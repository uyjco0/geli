package util.io.file;

import javax.swing.filechooser.FileFilter;

import java.io.File;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.
 *
 * It does not belong to the Public API.
 * 
 * 
 */

class FileExtensionFilter_internal extends FileFilter
{
    private Hashtable<String, FileExtensionFilter_internal> filters = null;
    private String description = null;
    private String fullDescription = null;
    private boolean useExtensionsInDescription = true;

    /**
     * Creates a file filter. If no filters are added, then all
     * files are accepted.
     */
    public FileExtensionFilter_internal()
    {
        this.filters = new Hashtable<String,FileExtensionFilter_internal>();
    }

    /**
     * Creates a file filter that accepts files with the given extension.
     * Example: new BDMFileFilter("jpg");
     */
    public FileExtensionFilter_internal(String extension)
    {
        this(extension, null);
    }

    /**
     * Creates a file filter that accepts the given file type.
     * Example: new BDMFileFilter("jpg", "JPEG Image Images");
     */
    public FileExtensionFilter_internal(String extension, String description)
    {
        this();

        if (extension != null)
        {
            this.addExtension(extension);
        }

        if (description != null)
        {
            this.setDescription(description);
        }
    }

    /**
     * Creates a file filter from the given string array.
     * Example: new BDMFileFilter(String {"gif", "jpg"});
     */
    public FileExtensionFilter_internal(String[] filters)
    {
        this(filters, null);
    }

    /**
     * Creates a file filter from the given string array and description.
     * Example: new BDMFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
     */
    public FileExtensionFilter_internal(String[] filters, String description)
    {
        this();

        for (int i = 0; i < filters.length; i++)
        {
            // add filters one by one
            this.addExtension(filters[i]);
        }

        if (description != null)
        {
            this.setDescription(description);
        }
    }

    /**
     * Return true if this file should be shown in the directory pane,
     * false if it shouldn't.
     */
    public boolean accept(File f)
    {
        if (f != null)
        {
            if (f.isDirectory())
            {
                return true;
            }

            String extension = getExtension(f);

            if ((extension != null) && (filters.get(getExtension(f)) != null))
            {
                return true;
            }

            ;
        }

        return false;
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

            ;
        }

        return null;
    }

    /**
     * Adds a filetype "dot" extension to filter against.
     *
     * For example: the following code will create a filter that filters
     * out all files except those that end in ".jpg" and ".tif":
     *
     *   BDMFileFilter filter = new BDMFileFilter();
     *   filter.addExtension("jpg");
     *   filter.addExtension("tif");
     *
     */
    public void addExtension(String extension)
    {
        if (filters == null)
        {
            filters = new Hashtable<String, FileExtensionFilter_internal>(5);
        }

        filters.put(extension.toLowerCase(), this);
        fullDescription = null;
    }

    /**
     * Returns the human readable description of this filter. For
     * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
     */
    public String getDescription()
    {
        if (fullDescription == null)
        {
            if ((description == null) || isExtensionListInDescription())
            {
                fullDescription = (description == null) ? "(" : (description
                    + " (");

                // build the description from the extension list
                Enumeration<String> extensions = filters.keys();

                if (extensions != null)
                {
                    fullDescription += ("." + extensions.nextElement());

                    while (extensions.hasMoreElements())
                    {
                        fullDescription += (", ."
                        + extensions.nextElement());
                    }
                }

                fullDescription += ")";
            }
            else
            {
                fullDescription = description;
            }
        }

        return fullDescription;
    }

    /**
     * Sets the human readable description of this filter. For
     * example: filter.setDescription("Gif and JPG Images");
     */
    public void setDescription(String description)
    {
        this.description = description;
        fullDescription = null;
    }

    /**
     * Determines whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     */
    public void setExtensionListInDescription(boolean b)
    {
        useExtensionsInDescription = b;
        fullDescription = null;
    }

    /**
     * Returns whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     */
    public boolean isExtensionListInDescription()
    {
        return useExtensionsInDescription;
    }

    /**
     * Clears list of file filters and descriptions
     */
    public void clear()
    {
        filters.clear();
    }
}
