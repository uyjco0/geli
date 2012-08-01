package util.io.file;

import util.text.StringList;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * 
 * Base class for all filetypes.
 *
 * Parsing of the lines when loading and construction of the lines when saving
 * is delegated to subclasses.
 *
 * Headers starting with % and comments starting with # are handled here,
 * globally.
 *
 * Many methods are implemented empty instead of declared abstract so they don't
 * have to be implemented by subclasses if they are not needed.
 * 
 * It does not belong to the Public API.
 * 
 * 
 */
abstract class BaseFile_internal
{
    /** Log4j logging. */
    protected static Logger log = Logger.getLogger(BaseFile_internal.class);

    /** Comment of file. */
    protected StringList comment;

    /** Header of file. */
    protected StringList header;

    /** Path of file. */
    protected File file;

    /** Current locale. */
    protected Locale loc;

    /** Current number format. */
    protected NumberFormat nf;

    /** Comment prefix. */
    protected String commentPrefix;

    /** Header prefix. */
    protected String headerPrefix;

    /** Default parsing delimiter characters. */
    protected String delimiters = " \t\n\r\f";

    /** Default saving delimiter character. */
    protected String delimiter = "\t";

    /** Flag whether to allow empty lines, usually false, except rules files */
    protected boolean allowEmptyLines = false;
    
    /** Hold the filetype of the file */
    protected FileType_internal ft;
    
    /** Defines if the file has data*/
    protected boolean parseD;
    /** Standard constructor */
    public BaseFile_internal()
    {
        super();
        this.parseD=true;
        this.commentPrefix = "#";
        this.headerPrefix = "%";
        this.comment = new StringList();
        this.header = new StringList();
       
        /* Set as default language Spanish */ 
        /* Use the lowercase two-letter ISO-639 code for the Spanish language */
        this.setLocale(new Locale("es"));
        
        /** this.setLocale(Locale.US); */
    }

    /**
     * Constructor with a filename
     *
     * @param filename
     *            Name of the file
     */
    public BaseFile_internal(String filename)
    {
        this();
        this.file = new File(filename);
    }

    /**
     * Check if a string represents a NaN.
     *
     * @param s
     *            String to check
     */
    protected static boolean isNaN(String s)
    {
        s = s.toLowerCase();

        return s.equals("?") || s.toLowerCase().equals("nan") 
	    || s.toLowerCase().equals("non") || s.toLowerCase().equals("nn") 
	    || s.toLowerCase().equals("n.n.") || s.trim().equals("");
    }

    /**
     * Check if a string represents a number
     *
     * @param s
     *            String to check
     */
    protected boolean isNumber(String s)
    {
        boolean r;

        try
        {
            nf.parse(s);
            r = true;
        }
        catch (ParseException e)
        {
            r = false;
        }

        return r;
    }

    /**
     * Write a StringList to a writer.
     *
     * @param w
     *            Writer to save data to
     * @param l
     *            List of strings to save
     * @param prefix
     *            Prefix to add in front of each line
     */
    protected static void writeStringList(BufferedWriter w, StringList l,
        String prefix) throws IOException
    {
        Iterator<String> iter = l.iterator();

        while (iter.hasNext())
        {
            w.write(prefix + iter.next());
            w.newLine();
        }
    }

    /**
     * Report a parsing error
     *
     * @param row
     *            line number
     * @param coll
     *            token number
     * @param msg
     *            error message
     */
    protected static void parseError(int row, int col, String msg)
    {
        if (col != -1)
        {
            log.warn("Error parsing file in line " + row + " column " + col
                + ": " + msg);
        }
        else
        {
            log.warn("Error parsing file in line " + row + ": " + msg);
        }
    }

    /**
     * Write a StringList to a writer.
     *
     * @param w
     *            Writer to save data to
     * @param l
     *            List of strings to save
     */
    protected static void writeStringList(BufferedWriter w, StringList l)
        throws IOException
    {
        writeStringList(w, l, "");
    }

    /**
    * Return the extension portion of the file's name .
    */
    public String getExtension(File f)
    {
        if (f != null)
        {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');

            if ((i > 0) && (i < (filename.length() - 1)))
            {
            	return filename.substring(i + 1).toLowerCase();
            }
        }

        return null;
    }
    
    
    /**
     * Determine if a line still contains header information or whether the data
     * lines started
     *
     * @param line
     *            Line to check
     */
    protected boolean isHeader(String line)
    {
        return false;
    }

    /**
     * Parse the comment in subclasses. 
     */
    protected void parseComment() throws IOException
    {
    }
    
    /**
     * Parse the header in subclasses. 
     */
    protected void parseHeader()
    {
    }
    
    /**
     * The subclasses defines if to parse data as a function of the extension of the file. 
     * If it detects any error returns TRUE
     */
    protected boolean parseData()
    {
    	this.parseD = true;
    	return false;
    }

    /**
     * Init data structures in subclasses given the number of lines in the file
     * and the number of tokens in the first line after header and comments.
     *
     * @param rows
     *            Number of lines in file.
     * @param cols
     *            Number of tokens in first data line.
     */
    protected void init(int rows, int cols)
    {
    }

    /**
     * Parse a line in subclasses to fill the data structures with values.
     *
     * @param row
     *            Number of current line.
     * @param line
     *            Current line.
     */
    protected void parseLine(int row, String line)
    {
    }

    /**
     * Check consistency of header line with size information.
     *
     * @param row
     *            Row of header where size is stored (starting with 0)
     */
    protected void checkHeaderAgainstSize(int row)
    {
        if (this.header.size() > row)
        {
            try
            {
                int size = nf.parse(this.header.getString(row)).intValue();

                if (size != this.getSize())
                {
                    log.warn("Wrong number of rows in header: " + "expected <"
                        + this.getSize() + "> but was <" + size + ">.");
                }
            }
            catch (ParseException e)
            {
                log.warn("Error parsing number rows in header: "
                    + this.header.getString(row));
            }
        }
    }

    /**
     * Check consistency of header information with data structure in
     * subclasses. Called after init/2 and parsing. Only issue warnings when
     * something is wrong.
     */
    protected void checkHeader()
    {
    }

    /**
     * Load data. Parsing of lines and header is delegated to subclasses.
     */
    public void load() throws IOException
    {
        log.debug("Loading " + this.getFile());
       
        // read in comments and header and count the data lines of the file
        LineNumberReader r = new LineNumberReader(new InputStreamReader(
                    new FileInputStream(this.getFile())));
        
        String line;
        int skip = 0;
        int rows = 0;
        String testLine = new String();
        boolean body = false;
        boolean end_space = false;
        
        String ext = this.getExtension(this.getFile());
        this.ft = new FileType_internal();
        this.ft.setExt("." + ext);
        
        while ((line = r.readLine()) != null)
        {
            if (line.startsWith(commentPrefix))
            {
                this.comment.add(line.substring(1).trim());
                skip++;
            }
            else if ((headerPrefix.length() > 0)&& line.startsWith(headerPrefix))
            {
                this.header.add(line.substring(headerPrefix.length()).trim());
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
                        this.header.add(line);
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
        
        int cols =0;
        if(testLine!=""){
        	cols = (new StringTokenizer(testLine, this.delimiters)).countTokens();
        }
        r.close();
        
        // Decides if it is necessary to parse the rest of the file
        boolean error = this.parseData();
        
        if(error)
    	{
        	log.debug("The extension of the file not agrees with the file");
    		System.exit(0);
    		
    	}
        
        try{
        	parseComment();
        }catch(IOException e){
        	System.out.println(e.getMessage());
        	log.debug(e.getMessage());
        	System.exit(0);
        }
    	
    	//Parse header, init structures and parse data
        if(parseD)
        {
        	parseHeader();
        	if (rows == 0)
            { 
                throw new IOException("No data in body of file");
            }
        	init(rows, cols);

        	// reopen file to parse data
        	r = new LineNumberReader(new InputStreamReader(new FileInputStream(this.getFile())));

        	// skip header and comment this time
        	for (int i = 0; i <= skip; i++)
        	{
        		line = r.readLine();
        	}

        	// parse rest of file
        	int row = 0;

        	do
        	{
        		parseLine(row++, line);
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
    }

    /** Load data from this file */
    public void load(String filename)throws IOException
    {
        this.setFilename(filename);
        this.load();
    }

    /**
     * Generate a fresh header based on the data structure for saving.
     */
    protected void buildHeader()
    {
        this.header = new StringList();
    }

    /**
     * Construct a line in subclasses for saving the data to a file.
     *
     * @param row
     *            Number of current line.
     * @return Line with that number.
     */
    protected String buildLine(int row)
    {
        return "";
    }

    /**
     * Save the data to a file. Construction of a line is delegated to
     * subclasses.
     */
    public void save() throws IOException
    {
        log.debug("Saving " + this.getFile());
        buildHeader();

        BufferedWriter w = new BufferedWriter(new FileWriter(this.getFile()));

        writeStringList(w, this.comment, commentPrefix + " ");
        writeStringList(w, this.header, headerPrefix);
        
        if(this.parseD){
        	for (int i = 0; i < this.getSize(); i++)
        	{
        		w.write(buildLine(i));
        		w.newLine();
        	}
        }

        w.close();
    }

    /** Save data to this file. */
    public void save(String filename)
        throws IOException
    {
        this.file = new File(filename);
        this.save();
    }

    /**
     * Get the number of lines in file.
     *
     * @return Number of lines in file.
     */
    public int getSize()
    {
        return 1;
    }

    /** Get the comment of file. */
    public StringList getComment()
    {
        return this.comment;
    }

    /** Change the comment of file. */
    public void setComment(StringList comment)
    {
        this.comment = comment;
    }

    /** Get the name of file. */
    public String getFilename()
    {
        return this.file.getPath();
    }

    /** Change the name of the file. */
    public void setFilename(String filename)
    {
        this.file = new File(filename);
    }

    /** Get the file. */
    public File getFile()
    {
        return this.file;
    }

    /** Get the header of file. */
    public StringList getHeader()
    {
        return this.header;
    }

    /** Get the current locale. */
    public Locale getLocale()
    {
        return this.loc;
    }

    /** Change the current locale. */
    public void setLocale(Locale loc)
    {
        this.loc = loc;
        this.nf = NumberFormat.getInstance(this.loc);
        this.nf.setGroupingUsed(false);
    }

    /** Get the current number format. */
    public NumberFormat getNumberFormat()
    {
        return this.nf;
    }
}
