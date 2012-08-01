package util.text;

import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.RESyntax;

import java.text.NumberFormat;

import java.util.HashSet;

/**
 * 
 * Utility methods for Strings.
 * 
 * It belongs to the Public API.
 *
 * @see SimilarString
 * 
 */
public class StringUtils
{
    /**
     * Return longer of two strings.
     * @param first First string.
     * @param second Second string.
     * @return Returns longer String.
     */
    public static String longer(String first, String second)
    {
        if (second.length() > first.length())
        {
            return second;
        }
        else
        {
            return first;
        }
    }

    /**
     * Build n-grams of string.
     * @param n Length of n-grams.
     * @param s String.
     * @return Returns StringList of n-grams.
     */
    public static StringList toNGrams(int n, String s)
    {
        StringList nGrams = new StringList();

        if ((n > 0) && (s != null) && (s.length() > 0) && (s.length() >= n))
        {
            int i = 0;

            while (i <= (s.length() - n))
            {
                nGrams.add(new String(s.substring(i, i + n)));
                i++;
            }
        }

        return nGrams;
    }

    /**
     * Build extended n-grams of string by adding _ at start and end.
     * @param n Length of n-grams.
     * @param s String.
     * @return Returns StringList of n-grams.
     */
    public static StringList toExtNGrams(int n, String s)
    {
        return toNGrams(n, "_" + s + "_");
    }

    /**
     * Calculate n-grams metric distance of two strings.
     * @param n Length of n-grams.
     * @param first First string.
     * @param second Second string.
     * @return Returns value of n-gram metric.
     */
    public static float nGramMetric(int n, String first, String second)
    {
        if (first.equals(second))
        {
            return 1;
        }
        else
        {
            StringList sGrams = toNGrams(n, first);
            StringList cGrams = toNGrams(n, second);
            HashSet<String> union = new HashSet<String>();

            union.addAll(sGrams.List());
            union.addAll(cGrams.List());

            HashSet<String> intersection = new HashSet<String>();

            intersection.addAll(sGrams.List());
            intersection.retainAll(cGrams.List());

            if (union.size() > 0)
            {
                float f = intersection.size();

                f /= union.size();

                return f;
            }
            else
            {
                return 0;
            }
        }
    }

    /**
     * Calculate extended n-grams metric distance of two strings.
     * @param n Length of n-grams.
     * @param first First string.
     * @param second Second string.
     * @return Returns value of extended n-gram metric.
     */
    public static float extNGramMetric(int n, String first, String second)
    {
        return nGramMetric(n, "_" + first + "_", "_" + second + "_");
    }

    /**
     * Replace parts of a string.
     * @param what String to replace.
     * @param with Replacement String.
     * @param s Replace all occurences in this String.
     * @return Returns new String.
     */
    public static String replace(String what, String with, String s)
    {
        String result = "";

        if ((what.length() > 0) && (s.length() > 0))
        {
            int i = 0;

            while (i < s.length())
            {
                if ((i <= (s.length() - what.length()))
                    && (s.substring(i, i + what.length()).equals(what)))
                {
                    result += with;
                    i += what.length();
                }
                else
                {
                    result += s.charAt(i++);
                }
            }
        }
        else
        {
            result = s;
        }

        return result;
    }

    /**
     * Remove all character from a string that don't match
     * the regular expression \W+
     * @param s Remove from this String.
     * @return Returns new String.
     */
    public static String removeNonWordChars(String s)
    {
        try
        {
            RE regexp = new RE("\\W+", RESyntax.RE_CHAR_CLASS_ESCAPES);

            return regexp.substituteAll(s, "");
        }
        catch (REException e)
        {
            return s;
        }
    }

    /**
     * Remove all Spanish articles from a String.
     * @param s Remove from this String.
     * @return Returns new String.
     */
    public static String removeArticles(String s)
    {
        try
        {
            RE regexp = new RE("(^|\\W+)(el|los|la|las|un|unos|una|unas)\\W+",
                    RESyntax.RE_CHAR_CLASS_ESCAPES);

            return regexp.substituteAll(s, " ").trim();
        }
        catch (REException e)
        {
            return s;
        }
    }


    /**
     * Normalize a String, that is
     * make it lowercase,
     * remove all no word characters like spacec and punctuation,
     * remove articles
     * @param s Normalize this String.
     * @return Returns new String.
     */
    public static String normalize(String s)
    {
        // lowercase, remove spaces, dots etc.
        s = removeArticles(s.toLowerCase());
        s = removeNonWordChars(s);

        return s;
    }

    

    /**
     * 
     * Count number of occurences of one String int another.
     * 
     * @param of 
     * 			Search for this String.
     * @param in 
     * 			Search in this String.
     * @return 
     * 			Returns number of occurences.
     */
    public static int occurrences(String of, String in)
    {
        int pos;
        int count = 0;
        int start = 0;

        while ((pos = in.indexOf(of, start)) != -1)
        {
            count++;
            start = pos + 1;
        }

        return count;
    }

    
    /**
     * Balance parentethese, braces and brackets
     * Could be much more sophisticated! later...
     * @param s Correct this String.
     * @return Returns new String.
     */
    public static String balance(String s)
    {
        s = balanceOne(s, "(", ")");
        s = balanceOne(s, "[", "]");
        s = balanceOne(s, "{", "}");

        return s;
    }

    /**
     * Balance open and closing strings
     * @param s Correct this String.
     * @return Returns new String.
     */
    protected static String balanceOne(String s, String open, String close)
    {
        // close any open
        int p1 = s.lastIndexOf(open);
        int p2 = s.lastIndexOf(close);

        if ((p1 != -1) && ((p2 == -1) || (p2 < p1)))
        {
            s = s + close;
        }

        // open any closed
        p1 = s.lastIndexOf(open);
        p2 = s.lastIndexOf(close);

        if ((p2 != -1) && ((p1 == -1) || (p1 > p2)))
        {
            s = open + s;
        }

        return s;
    }

    /**
     * Right align a string by adding spaces on the left up to specified length.
     * @param text Text to align
     * @param length Length of result
     * @return Text right aligned
     */
    public static String alignRight(String text, int length)
    {
        StringBuffer result = new StringBuffer();

        for (int i = 1; i <= (length - text.length()); i++)
        {
            result.append(" ");
        }

        result.append(text);

        return result.toString();
    }

    /**
     * Right align a number by adding spaces on the left up to specified length.
     * @param value Number to align
     * @param length Length of result
     * @return Number right aligned
     */
    public static String alignRight(int value, int length)
    {
        return alignRight(String.valueOf(value), length);
    }

    /**
     * Text whether string is an integer
     * @param s String
     * @return True if string is an integer
     */
    public static boolean isInt(String s)
    {
        try
        {
            Integer.parseInt(s);

            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * Format file size
     * @param s file size
     * @return file size as string
     */
    public static String formatFilesize(int s)
    {
        if (s < 1024)
        {
            return s + "B";
        }
        else
        {
            NumberFormat nf = NumberFormat.getInstance();

            nf.setMaximumFractionDigits(1);
            nf.setMinimumFractionDigits(1);

            if (s < (1024 * 1024))
            {
                return nf.format((double) s / 1024) + "KB";
            }
            else if (s < (1024 * 1024 * 1024))
            {
                return nf.format((double) s / 1024 / 1024) + "MB";
            }
            else if (s < (1024 * 1024 * 1024 * 1024))
            {
                return nf.format((double) s / 1024 / 1024 / 1024) + "GB";
            }
            else
            {
                return nf.format((double) s / 1024 / 1024 / 1024 / 1024) + "TB";
            }
        }
    }
}
