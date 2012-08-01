package util.text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * A HashMap to count occurences of Strings.
 * 
 * It belongs to the Public API.
 * 
 * 
 */
public class StringCounter
{
	
	private HashMap<String,Integer> map;
	
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Standard constructor without arguments.
     */
    public StringCounter()
    {
        this.map = new HashMap<String,Integer>() ;
    }

    /*
     * Copy constructor.
     * @param c Other StringCounter
     */
    public StringCounter(StringCounter c)
    {
       this.map = c.map;
    }

    /*
     * Constructor that adds StringList right away.
     * @param token StringList with tokens to start with.
     */
    public StringCounter(StringList tokens)
    {
    	this.map = new HashMap<String,Integer>() ;

        Iterator<String> iter = tokens.iterator();

        while (iter.hasNext())
        {
            this.add(iter.next());
        }
    }

    /*
     * Returns true if this map contains a mapping for the specified key.
     * @param key String with the key.
     */
    public boolean containsKey(String key)
    {
    	return this.map.containsKey(key);
    }
    
    
    /*
     * Returns the value to which the specified key is mapped in this identity hash map, or null if the map contains no mapping for this key.
     * @param key String with the key.
     */
    public Integer get(String key)
    {
    	return this.map.get(key);
    }
    
    
    /*
     * Associates the specified value with the specified key in this map.
     * @param key String with the key, and val Integer with the value.
     */
    public void put(String key, Integer val)
    {
    	this.map.put(key, val);
    }
    
    /*
     * Removes the mapping for this key from this map if present
     * @param key String with the key.
     */
    public void remove(String key)
    {
    	this.map.remove(key);
    }
    
    
    /*
     * Returns a set view of the keys contained in this map.
     */
    public Set<String> keySet()
    {
    	return this.map.keySet();
    }
    
    
    /*
     * Add occurence of a String.
     * If not in Hash yet, it is added with 1
     * otherwise the value stored is increased by one.
     * @param value String to add.
     */
    public void add(String value)
    {
        if (this.containsKey(value))
        {
            int count = (this.get(value)).intValue();

            this.put(value, new Integer(count + 1));
        }
        else
        {
            this.put(value, new Integer(1));
        }
    }

    /*
     * Subtract a String.
     * If it is the Hash the value stored is decreased by one.
     * If the value if 0, the String is removed.
     * @param value String to subtract.
     */
    public void subtract(String value)
    {
        if (this.containsKey(value))
        {
            int count = (this.get(value)).intValue();

            if (count > 1)
            {
                this.put(value, new Integer(count - 1));
            }
            else
            {
                this.remove(value);
            }
        }
    }

    /*
     * Get stored number of occurences of a String.
     * @param key String to query.
     * @return Returns number associated with this String.
     */
    public int getCount(String key)
    {
        Integer count = this.get(key);

        if (count == null)
        {
            return 0;
        }
        else
        {
            return count.intValue();
        }
    }

    /*
     * Get most frequent String.
     * @param key String to query.
     * @return Returns most frequent String.
     */
    public String getMostFrequent()
    {
        String key;
        String value = "";
        int count;
        int max = 0;
        Iterator<String> iter = this.keySet().iterator();

        while (iter.hasNext())
        {
            key = iter.next();
            count = (this.get(key)).intValue();

            if (count > max)
            {
                max = count;
                value = key;
            }
        }

        return value;
    }

    /*
     * Get least frequent String.
     * @param key String to query.
     * @return Returns least frequent String.
     */
    public String getLeastFrequent()
    {
        String key;
        String value = "";
        int count;
        int min = Integer.MAX_VALUE;
        Iterator<String> iter = this.keySet().iterator();

        while (iter.hasNext())
        {
            key = iter.next();
            count = (this.get(key)).intValue();

            if (count < min)
            {
                min = count;
                value = key;
            }
        }

        return value;
    }
}
