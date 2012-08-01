package util.io.file;

import org.apache.log4j.Logger;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import util.text.StringList;

import java.text.ParseException;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import java.io.FileWriter;

import java.io.IOException;

/**
 *
 * @author Jorge Couchet
 * 
 */
public class GenericFile extends BaseFile_internal{

    /** Log4j logging. */
    protected static Logger log = Logger.getLogger(GenericFile.class);
    
    /** Matrix with the information of the CON file (the attributes of the packets). */
    protected DoubleMatrix2D data;
    
    protected int rows;
    protected int columns;
    
    protected boolean isForSvm;
    
     
    
    /** Standard constructor */
    public GenericFile()
    {
    	super();
    	this.isForSvm = false;
    	
    }
    
    
    public boolean getIsForSvm(){
    	return this.isForSvm;
    }
    
    
    public void setIsForSVM(boolean isSvm){
    	this.isForSvm = isSvm;
    }
    
    
    public DoubleMatrix2D getData()
    {
    	return this.data;
    }
    
    
    public void setData(DoubleMatrix2D data)
    {
    	this.data = data;
    }
    
    
    public int getRows()
    {
        return this.rows;
    }
    
    public void setRows(int rows)
    {
        this.rows = rows;
    }
    
    
    public int getColumns()
    {
        return this.columns;
    }
    
    public void setColumns(int columns)
    {
        this.columns = columns;
    }
    
    
    public double getDataElem(int row, int col)
    {
    	    	
    	return (this.data).get(row,col);
    	
    }
    
    
    public void setDataElem(int row, int col, double elem)
    {
    	
    	(this.data).set(row, col, elem);
    	
    }
    
    
       
    
  /** Generate header. */
    protected void buildHeader()
    {
    	      
    	super.buildHeader();
    	
    	String row = Integer.toString(this.rows);
    	String col = Integer.toString(this.columns);
    	this.header.add(row  + " "  + col);
    	
   }
    
    
    protected void parseHeader()
    {
        StringTokenizer st = new StringTokenizer(this.header.getString(0), this.delimiters);
    			
        try
    	{
    		this.rows = nf.parse(st.nextToken()).intValue();
    	}
    	catch (ParseException e)
    	{
    		log.warn("Error parsing number of rows in header: ");
    	}

    	try
    	{
    		this.columns = (nf.parse(st.nextToken()).intValue());
    	}
    	catch (ParseException e)
    	{
    		log.warn("Error parsing number of columns in header: ");
    	}
   }
    
    
   
    protected String buildLine(int row)
    {
        StringBuffer s = new StringBuffer();
        
        if(this.isForSvm){
        	
        	this.delimiter = " ";
        	
        	//Append the label
        	if((this.data).get(row,0) >= 0){
        		s.append("+");
        	}
        	else{
        		s.append("-");
        	}
        	
        	(this.nf).setMaximumFractionDigits(0);
        	s.append(nf.format(Math.abs((this.data).get(row,0))));
        	s.append(this.delimiter);
        	(this.nf).setMaximumFractionDigits(6);
        	
        	for (int i = 1; i < this.columns; i++){
        		
        		//Append the index of the value
        		s.append(i);
        		s.append(":");
        		//Append the value
        		s.append(nf.format((this.data).get(row,i)));
	        	s.append(this.delimiter);
        		
        	}
        }else{
        
	        for (int i = 0; i < this.columns; i++)
	        {
	        	s.append(nf.format((this.data).get(row,i)));
	        	s.append(this.delimiter);
	        }
       }
        
        return s.substring(0, s.length() - this.delimiter.length());
    }
    
    
    
    /**
     * Init data structures given the number of lines in the file
     * and the number of tokens in the first line after header and comments.
     *
     * @param rows
     *            Number of lines in file.
     * @param cols
     *            Number of tokens in first data line.
     */
    protected void init(int rows, int cols)
    {
    	this.data = new DenseDoubleMatrix2D(rows, cols);
    }
    
    
    protected void parseLine(int row, String line)
    {
        StringTokenizer st = new StringTokenizer(line, this.delimiters);
        DoubleMatrix2D d;
        //Start in one, because in the first place (index =0) goes the key
        int col = 0;
        int column;
        String t;
      
        d = this.data;
        column = this.columns;
        
        // I need it to conserve all the decimal of the file
        (this.nf).setMaximumFractionDigits(6);
        
        
        while (st.hasMoreTokens())
        {
            t = st.nextToken();
            
            if (col < column)
            {
                try
                {
                	// convert Inf to NaN
                	t = t.replaceAll("Inf", "NaN");

                	// convert scientific notations
                	t = t.replaceAll("[Ee]\\+?", "E");
                    d.set(row, col, nf.parse(t).doubleValue());
                }
                catch (ParseException e)
                {
                    if (!BaseFile_internal.isNaN(t))
                    {
                        BaseFile_internal.parseError(row, col, e.getMessage());
                    }

                    d.set(row, col, Double.NaN);
                }
            }
            else
            {
                BaseFile_internal.parseError(row, -1,
                    "Extra data after expected end of line!" + t);
            }

            col++;
            
        }
               
        if (col < column)
        {
            BaseFile_internal.parseError(row, col, "Not enough columns!");
        }
    } 
    
    
    
    protected void parseLineWithoutHeader(int row, String line)
    {
        StringTokenizer st = new StringTokenizer(line, this.delimiters);
        DoubleMatrix2D d;
        //Start in one, because in the first place (index =0) goes the key
        int col = 0;
        int column;
        String t;
      
        d = this.data;
        column = this.columns;
        
        // I need it to conserve all the decimal of the CON file
        (this.nf).setMaximumFractionDigits(6);
        
        
        while (st.hasMoreTokens())
        {
            t = st.nextToken();
            
            if((t != "") && (t != " ")){
                
            	try
                {
                	// convert Inf to NaN
                	t = t.replaceAll("Inf", "NaN");

                	// convert scientific notations
                	t = t.replaceAll("[Ee]\\+?", "E");
                    d.set(row, col, nf.parse(t).doubleValue());
                }
                catch (ParseException e)
                {
                    if (!BaseFile_internal.isNaN(t))
                    {
                        BaseFile_internal.parseError(row, col, e.getMessage());
                    }

                    d.set(row, col, Double.NaN);
                }
            
                col++;
            }
            
        }
               
        if (col < column)
        {
            BaseFile_internal.parseError(row, col, "Not enough columns!");
        }
    } 
    
    
    
    
    public void load() throws IOException
    {
    	File f;
    	StringList headeraux;
    	
    	f = this.getFile();
    	headeraux = this.header;
    	
    	
        log.debug("Loading " + f);
        // read in comments and header and count the data lines of the file
        LineNumberReader r = new LineNumberReader(new InputStreamReader(
                    new FileInputStream(f)));
        String line;
        int skip = 0;
        int rows = 0;
        String testLine = new String();
        boolean body = false;
        boolean end_space = false;

        while ((line = r.readLine()) != null)
        {
            if (line.startsWith(commentPrefix))
            {
                this.comment.add(line.substring(1).trim());
                skip++;
            }
            else if ((headerPrefix.length() > 0)
                && line.startsWith(headerPrefix))
            {
                headeraux.add(line.substring(headerPrefix.length()).trim());
                skip++;
            }
            else if (!allowEmptyLines && (line.trim().length() == 0) && !body)
            {
                skip++;
            }
            else
            {
                if (!body)
                {
                    // still header information?
                    if (isHeader(line))
                    {
                        headeraux.add(line);
                        skip++;
                    }
                    else
                    {
                        // save first data line to determine number of data
                        // fields
                        body = true;
                        testLine = line;
                        rows++;
                    }
                }
                else
                {
                    // determine end of data block
                    if (!allowEmptyLines && !end_space
                        && (line.trim().length() == 0))
                    {
                        end_space = true;
                    }

                    if (!end_space)
                    {
                        rows++;
                      
                    }
                }
            }
        }

        int cols = (new StringTokenizer(testLine, this.delimiters)).countTokens();

        r.close();

        if (rows == 0)
        { 
            throw new IOException("No data in body of file");
        }

        // parse header and init data structures
        parseHeader();
        init(rows, cols);
        
        
        // reopen file to parse data
        r = new LineNumberReader(new InputStreamReader(
                    new FileInputStream(f)));

        // skip header and comment this time
        for (int i = 0; i <= skip; i++)
        {
            line = r.readLine();
        }

        // parse rest of file
        int row = 0;

        do
        {
        	parseLine(row, line);
            row ++;
        }
        while (((line = r.readLine()) != null) && (row < rows));

        r.close();

        // check header, but...
        try
        {
            this.checkHeader();
        }
        catch (Exception ex)
        {
            // ...ignore any errors
        }
    }
    
    
    public void loadWithoutHeader(int col) throws IOException
    {
    	File f;
    	int cols = col;
    	
    	f = this.getFile();
    	
        log.debug("Loading " + f);
        
        // read in comments and header and count the data lines of the file
        LineNumberReader r = new LineNumberReader(new InputStreamReader(new FileInputStream(f)));
        String line;
        int rows = 0;
        boolean end_space = false;

        while ((line = r.readLine()) != null)
        {
           
        	// determine end of data block
            if ((!allowEmptyLines) && (!end_space) && (line.trim().length() == 0))
            {
            	end_space = true;
            }

            if (!end_space)
            {
            	rows++;
                      
            }
                
        }

        
        r.close();

        if (rows == 0)
        { 
            throw new IOException("No data in body of file");
        }

        // init data structures
        init(rows, cols);
        this.rows = rows;
        this.columns = cols;
        
        
        // reopen file to parse data
        r = new LineNumberReader(new InputStreamReader(new FileInputStream(f)));

        line = r.readLine();
        
        int row = 0;

        do
        {
        	parseLineWithoutHeader(row, line);
            row ++;
        }
        while (((line = r.readLine()) != null) && (row < rows));

        r.close();

        
    }
    
    
    /** Load data from filename */
    public void load(String filename)
        throws IOException
    {
        this.setFilename(filename);
        this.load();
    }
    
    
    /** Load data from filename */
    public void loadWithoutHeader(String filename, int col)
        throws IOException
    {
        this.setFilename(filename);
        this.loadWithoutHeader(col);
    }
        
    
    public void save() throws IOException
    {
    	File f;
    	int row = 0;
    	StringList headeraux;
    	
    	buildHeader();
    	f = this.getFile();
    	row = this.rows;
    	headeraux = this.header;
    	
         log.debug("Saving " + f);
         

         BufferedWriter w = new BufferedWriter(new FileWriter(f));
         
         writeStringList(w, this.comment, commentPrefix + " ");
         writeStringList(w, headeraux, headerPrefix);
         
         
         for (int i = 0; i < row; i++)
         {
        	 w.write(buildLine(i));
             w.newLine();
        }
         w.close();
    }
    
    
    /** Save data to filename. */
    public void save(String filename)
        throws IOException
    {
    	this.setFilename(filename);
    	this.save();
    }
    
    
    
}
