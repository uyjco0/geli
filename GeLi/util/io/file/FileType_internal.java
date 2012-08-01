package util.io.file;

import org.apache.log4j.Logger;

/**
 * Class to describe file types.
 * 
 * It does not belong to the Public API.
 * 
 *
 */
class FileType_internal
{
    /** log4j logging */
    protected static Logger log = Logger.getLogger(FileType_internal.class);
    protected String name;
    protected String ext;
    protected String description;

    /** Set the name of file type */
    public void setName(String name)
    {
        this.name = name;
    }

    /** Get the name of file type */
    public String getName()
    {
        return this.name;
    }

    /** Set the extension of file type */
    public void setExt(String newExt)
    {
        this.ext = newExt;
    }

    /** Get the extension of file type */
    public String getExt()
    {
        return this.ext;
    }

    /** Get the extension as a filter for file dialogs */
    public String getFilter()
    {
        if (this.ext.startsWith("_"))
        {
            return this.ext.substring(6);
        }
        else
        {
            return this.ext.substring(1);
        }
    }

    /** Set the description of file type */
    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }

    /** Get the description of file type */
    public String getDescription()
    {
        return this.description;
    }

    /** Equals() only depends on the extension. */
    public boolean equals(Object o)
    {
        try
        {
            return this.ext.equals(((FileType_internal) o).getExt());
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    /** Generic filetype */
    public static FileType_internal createFileType(String ext)
    {
        FileType_internal ft = new FileType_internal();

        ft.setExt(ext);
        ft.setName("*" + ext);

        return ft;
    }
}
