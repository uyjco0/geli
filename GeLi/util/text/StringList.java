package util.text;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * A LinkedList of Strings.
 * 
 * It belongs to the Public API.
 * 
 */
public class StringList
{
	private LinkedList<String> list;
	
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Standard constructor without arguments.
     */
    public StringList()
    {
    	this.list = new LinkedList<String>();
    }

    /*
     * Copy constructor.
     * @param list List of strings.
     */
    public StringList(StringList list)
    {
        this.list=new LinkedList<String>(list.list);
    }

    /**
     * Constructor with a collection of objects.
     * Each object is converted to a string with toString()
     * 
     * @param collection
     * 					 Collection of objects.
     */
    @SuppressWarnings("unchecked")
	public StringList(Collection collection)
    {
    	this.list = new LinkedList<String>();

        Iterator iter = collection.iterator();

        while (iter.hasNext())
        {
            this.list.add(iter.next().toString());
        }
    }

    /**
     * Constructor with a single string.
     * List is initialized with that string.
     * @param s A string.
     */
    public StringList(String s)
    {
    	this.list = new LinkedList<String>();

        if (s != null)
        {
            this.list.add(s);
        }
    }

    
    /**
     * Return the size of the list
     */
    public int size()
    {
    	return this.list.size();
    }
    
    
    /**
     * Return the iterator of the list
     */
    public Iterator<String> iterator()
    {
    	return this.list.iterator();
    }
    
    /**
     * Return the LinkedList
     */
    public LinkedList<String> List()
    {
    	return this.list;
    }
    
    /**
     * Add an item to the StringList
     * @param item to add
     */
    public void add(String item)
    {
        this.list.add(item);
    }
    
    /**
     * Add another StringList
     * @param items Items to add
     */
    public void addAll(StringList items)
    {
        this.list.addAll(items.list);
    }

    /**
     * Add another StringList with a prefix in front
     * of each item
     * @param items Items to add
     * @param prefix prefix
     */
    public void addAll(StringList items, String prefix)
    {
        Iterator<String> iter = items.list.iterator();

        while (iter.hasNext())
        {
            this.list.add(prefix + iter.next());
        }
    }

    /**
     * Get a string by index.
     * @param index Index of string in list
     * @return String at this index
     */
    public String getString(int index)
    {
        return this.list.get(index);
    }

    /**
     * Get first string.
     * @return First string.
     */
    public String getFirstString()
    {
        return this.list.getFirst();
    }

    /**
     * Get last string.
     * @return Last string.
     */
    public String getLastString()
    {
        return this.list.getLast();
    }

    /**
     * Get string representation, items seperated with given separator.
     * @param separator Charachter to separate items.
     * @return Concatenated string of items.
     */
    public String toString(String separator)
    {
        String result = "";

        if (this.size() > 0)
        {
            Iterator<String> iter = this.iterator();

            while (iter.hasNext())
            {
                result += (iter.next() + separator);
            }

            result = result.substring(0, result.length() - separator.length());
        }

        return result;
    }

    /**
     * Get string representation, items seperated with space.
     * @return Concatenated string of items separated with space.
     */
    public String toString()
    {
        return this.toString(" ");
    }

    /**
     * Return string list as array
     * @return string array
     */
    public String[] toStringArray()
    {
        if (this.size() > 0)
        {
            String[] a = new String[this.size()];

            for (int i = 0; i < this.size(); i++)
            {
                a[i] = this.getString(i);
            }

            return a;
        }
        else
        {
            return null;
        }
    }

    /**
     * Find most frequent string in list.
     * @return Returns most frequent string.
     */
    public String getMostFrequent()
    {
        StringCounter counter = new StringCounter(this);

        return counter.getMostFrequent();
    }

    /**
     * Find least frequent string in list.
     * @return Returns least frequent string.
     */
    public String getLeastFrequent()
    {
        StringCounter counter = new StringCounter(this);

        return counter.getLeastFrequent();
    }

    /**
     * Get StringList without duplicates.
     * @return Returns list without any duplicates.
     */
    @SuppressWarnings("unchecked")
	public StringList getDistinct()
    {
        HashSet<String> set = new HashSet<String>(this.list);

        return new StringList((Collection) set);
    }

    /**
     * Get count of distinct strings.
     * @return Returns size of getDistinct()
     */
    public int getDistinctCount()
    {
        return getDistinct().size();
    }

    /**
     * Get index of string similar to value.
     * @return Index of string found.
     */
    public int similarIndexOf(String value)
    {
        SimilarString compare = new SimilarString(value);

        compare.normalize();

        int index = -1;
        int i = 0;

        while (i < this.size())
        {
            SimilarString s = new SimilarString(getString(i));

            s.normalize();

            if (compare.extNGramMetric(3, s.toString()) > 0.5)
            {
                index = i;

                break;
            }

            i++;
        }

        return index;
    }
}
