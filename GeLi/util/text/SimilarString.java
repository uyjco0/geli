package util.text;

import java.util.Iterator;

/**
 * A Strings that can be compared to other strings byte common metrics.
 * 
 * It belongs to the Public API.
 *
 * @see StringUtils
 */
public class SimilarString
{
    /**
     * Field to hold actual String value.
     */
    private String iSimilar;

    /**
     * Standard constructor without arguments.
     */
    public SimilarString()
    {
        iSimilar = new String();
    }

    /*
     * Copy constructor.
     * @param value Other string
     */
    public SimilarString(String value)
    {
        iSimilar = new String(value);
    }

    /**
     * String representation
     * @return Returns the string.
     */
    public String toString()
    {
        return iSimilar;
    }

    /**
     * Build n-grams of string.
     * @param n Length of n-grams.
     * @return Returns StringList of n-grams.
     */
    public StringList toNGrams(int n)
    {
        return StringUtils.toNGrams(n, this.toString());
    }

    /**
     * Build extended n-grams of string by adding _ at start and end.
     * @param n Length of n-grams.
     * @return Returns StringList of n-grams.
     */
    public StringList toExtNGrams(int n)
    {
        return StringUtils.toExtNGrams(n, this.toString());
    }

    /**
     * Calculate n-grams metric distance of string and argument.
     * @param n Length of n-grams.
     * @param compare String to compare to.
     * @return Returns value of n-gram metric.
     */
    public float nGramMetric(int n, String compare)
    {
        return StringUtils.nGramMetric(n, this.toString(), compare);
    }

    /**
     * Calculate extended n-grams metric distance of string and argument.
     * @param n Length of n-grams.
     * @param compare String to compare to.
     * @return Returns value of extended n-gram metric.
     */
    public float extNGramMetric(int n, String compare)
    {
        return StringUtils.extNGramMetric(n, this.toString(), compare);
    }

    /**
     * Count how many Strings in this list are similar to this String
     * based on the trigram metric and a threshhold of 0.5.
     * @param compare Strings to compare to.
     * @return Returns number of similar Strings.
     */
    @SuppressWarnings("unchecked")
	public int countSimilarExtTrigramMetric(StringList compare)
    {
        int count = 0;
        Iterator iter = compare.iterator();

        while (iter.hasNext())
        {
            SimilarString value = new SimilarString((String) iter.next());

            value.normalize();

            float metric = this.extNGramMetric(3, value.toString());

            if (metric > 0.5)
            {
                count++;
            }
        }

        return count;
    }

    /**
     * Normalize the String, that is
     * make it lowercase,
     * remove all no word characters like spacec and punctuation,
     * remove articles
     * replace German Umlaute
     * @return Returns new String.
     */
    public String normalize()
    {
        iSimilar = StringUtils.normalize(this.toString());

        return iSimilar;
    }

    /**
     * Check whether the String is similar to another String.
     * As similar qualifies:
     * 1) equal
     * 2) both not empty and contained int one another
     * 3) trigram metric greater than 0.5
     * @return Whether it's similar.
     */
    public boolean isSimilarTo(String compare)
    {
        boolean ok = (this.toString().equals(compare)
            || ((this.toString().length() > 0) && (compare.length() > 0)
            && ((this.toString().indexOf(compare) != -1)
            || (compare.indexOf(this.toString()) != -1)
            || (this.extNGramMetric(3, compare) > 0.5))));

        return ok;
    }

    /**
     * Check whether the Strings are similar
     * As similar qualifies:
     * 1) equal
     * 2) both not empty and contained int one another
     * 3) trigram metric greater than 0.5
     * @param first First string.
     * @param second Second string.
     * @return Whether it's similar.
     */
    public static boolean similar(String first, String second)
    {
        if (first == null)
        {
            first = "";
        }

        if (second == null)
        {
            second = "";
        }

        SimilarString f = new SimilarString(first);

        f.normalize();

        SimilarString s = new SimilarString(second);

        s.normalize();

        return f.isSimilarTo(s.toString());
    }
}
