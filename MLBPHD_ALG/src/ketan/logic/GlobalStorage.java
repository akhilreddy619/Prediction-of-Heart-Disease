 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.logic;

/**
 *
 * @author PRASAD
 */
public class GlobalStorage
{
    public static String role;
    public static int counter;
    private static String whom;
    public static String dotorname;
        private static double[] ratios;
        public static int rowscount;
        public static void setWhom(String user)
        {
             whom=user;
        }
        public static String getWhom()
        {
            return(whom);
        }
        public static void setFactorRatios(double []ratiovalues)
        {
            ratios=ratiovalues;
        }
        public static double[] getFactorRatios()
        {
            return(ratios);
        }
}
