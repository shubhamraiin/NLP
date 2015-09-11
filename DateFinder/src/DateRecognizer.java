/*
 This class defines different date patterns which may be encountered in the text and print it
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateRecognizer{
	
   public static  void  DateFinder(String file,int number)
   {
       switch(number)
               {
           case 1:
        	   //matches dates like the 1st of April 2015
               String pattern = "(( the)?[0-9]{1,2}(st|th|rd|nd)?( of)?( January| February| March| April| May| June| July| August| September| October| November| December)( [0-9]{2,4})?)+";
               
               Pattern p = Pattern.compile(pattern);
               Matcher m = p.matcher(file);
        
                while(m.find())
                {
                    System.out.println(m.group());
         
                }
                break;
           case 2:
        	 //matches dates like Monday 23rd of January 2015
               String pattern1 = "((Monday |Tuesday |Wednesday |Thursday |Friday |Saturday |Sunday )?(January|February|March|April|May|June|July|August|September|October|November|December)( |-)[0-9]{1,2}(st|th|rd|nd)?( of)?( [0-9]{2,4})?)+";
               file = file.replaceAll("([0-9]{1,2}(st|th|rd|nd)?( of)?( January| February| March| April| May| June| July| August| September| October| November| December)( [0-9]{2,4})?)","");
               file = file.replaceAll("((([Ff]irst)|([Ss]econd)|([Tt]hird)|([Ff]ourth)|([Ff]ifth)|([Ss]ixth)|([Ss]eventh)|([Ee]ighth)|([Nn]inth)|([Tt]enth)|([Ee]leventh)|([Tt]welfth)|([Tt]hirteenth)|([Ff]ourteenth)|([Ff]ifteenth)|([Ss]ixteenth)|([Ss]eventeenth)|([Ee]ighteenth)|([Nn]ineteenth)|([Tt]wentieth)|([Tt]wenty(-| )[Ff]irst)|([Tt]wenty(-| )[Ss]econd)|([Tt]wenty(-| )[Tt]hird)|([Tt]wenty(-| )[Ff]ourth)|([Tt]wenty(-| )[Ff]ifth)|([Tt]wenty(-| )[Ss]ixth)|([Tt]wenty(-| )[Ss]eventh)|([Tt]wenty(-| )[Ee]ight)|([Tt]wenty(-| )[Nn]inth)|([Tt]hirtieth)|([Tt]hirtieth(-| )[Ff]irst))( of)?( January| February| March| April| May| June| July| August| September| October| November| December)( [0-9]{2,4})?)","");
               Pattern p1 = Pattern.compile(pattern1);
               Matcher m1 = p1.matcher(file);
        
                while(m1.find())
                {
                    System.out.println(m1.group());
         
                }
               break;
           case 3:
        	 //matches dates like 02-06-2015 or 02/06/2015
               String pattern2 = "( [0-9]{1,2}(/|-)[0-9]{1,2}((/|-)[0-9]{2,4})?)+";
               Pattern p2 = Pattern.compile(pattern2);
               Matcher m2 = p2.matcher(file);
        
                while(m2.find())
                {
                    System.out.println(m2.group());
         
                }
               break;
              
            case 4:
            	//matches dates like 2015-06-02 
               String pattern3 = "([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})+";
               Pattern p3 = Pattern.compile(pattern3);
               Matcher m3 = p3.matcher(file);
        
                while (m3.find()) {
                    System.out.println(m3.group());

                }
               break;
             
            case 5:
            	//matches dates like 9 Feb 2015
                String pattern4 = "([0-9]{1,2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)(-[0-9]{2,4})?)+";
                Pattern p4 = Pattern.compile(pattern4);
                Matcher m4 = p4.matcher(file);
                while(m4.find()){
                    System.out.println(m4.group());
                }
                break;
            
            case 6:
            	//matches dates like Second of July 2015
                String pattern5 = "((([Ff]irst)|([Ss]econd)|([Tt]hird)|([Ff]ourth)|([Ff]ifth)|([Ss]ixth)|([Ss]eventh)|([Ee]ighth)|([Nn]inth)|([Tt]enth)|([Ee]leventh)|([Tt]welfth)|([Tt]hirteenth)|([Ff]ourteenth)|([Ff]ifteenth)|([Ss]ixteenth)|([Ss]eventeenth)|([Ee]ighteenth)|([Nn]ineteenth)|([Tt]wentieth)|([Tt]wenty(-| )[Ff]irst)|([Tt]wenty(-| )[Ss]econd)|([Tt]wenty(-| )[Tt]hird)|([Tt]wenty(-| )[Ff]ourth)|([Tt]wenty(-| )[Ff]ifth)|([Tt]wenty(-| )[Ss]ixth)|([Tt]wenty(-| )[Ss]eventh)|([Tt]wenty(-| )[Ee]ight)|([Tt]wenty(-| )[Nn]inth)|([Tt]hirtieth)|([Tt]hirtieth(-| )[Ff]irst))( of)?( January| February| March| April| May| June| July| August| September| October| November| December)( [0-9]{2,4})?)+";
                Pattern p5 = Pattern.compile(pattern5);
                Matcher m5 = p5.matcher(file);
                while(m5.find()){
                    System.out.println(m5.group());
                }
                break;
            case 7:
            	//matches dates like May the Fourth of 2015
                String pattern6 = "((January|February|March|April|May|June|July|August|September|October|November|December)( the)?(( [Ff]irst)|( [Ss]econd)|( [Tt]hird)|( [Ff]ourth)|( [Ff]ifth)|( [Ss]ixth)|( [Ss]eventh)|( [Ee]ighth)|( [Nn]inth)|( [Tt]enth)|( [Ee]leventh)|( [Tt]welfth)|( [Tt]hirteenth)|( [Ff]ourteenth)|( [Ff]ifteenth)|( [Ss]ixteenth)|( [Ss]eventeenth)|( [Ee]ighteenth)|( [Nn]ineteenth)|( [Tt]wentieth)|( [Tt]wenty(-| )[Ff]irst)|( [Tt]wenty(-| )[Ss]econd)|( [Tt]wenty(-| )[Tt]hird)|( [Tt]wenty(-| )[Ff]ourth)|( [Tt]wenty(-| )[Ff]ifth)|( [Tt]wenty(-| )[Ss]ixth)|( [Tt]wenty(-| )[Ss]eventh)|( [Tt]wenty(-| )[Ee]ight)|( [Tt]wenty(-| )[Nn]inth)|( [Tt]hirtieth)|( [Tt]hirtieth(-| )[Ff]irst))( of)?( [0-9]{1,4})?)+";
                Pattern p6 = Pattern.compile(pattern6);
                Matcher m6 = p6.matcher(file);
                while(m6.find()){
                    System.out.println(m6.group());
                }
                break;
            case 8:
            	//matches different holidays like Christmas day
            	String pattern7 = "((New Years Day)|(Martin Luther King Jr Day)|(Day After Christmas)|(Memorial Day)|(Independence Day)|(Labor Day)|(Columbus Day)|(Veterans Day)|(Day after Thanksgiving)|(Thanksgiving( Day)?)|(Christmas Eve)|(Christmas( Day)?)|(Halloween)|(Valentines Day)|(New Years Eve))+";
                Pattern p7 = Pattern.compile(pattern7);
                Matcher m7 = p7.matcher(file);
                while(m7.find()){
                    System.out.println(m7.group());
                }
                break;
            case 9:
            	//matches dates like Feb 6
                String pattern8 = "((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{1,2}))+";
                file = file.replaceAll("([0-9]{1,2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)(-[0-9]{2,4})?)","");
                Pattern p8 = Pattern.compile(pattern8);
                Matcher m8 = p8.matcher(file);
                while(m8.find()){
                    System.out.println(m8.group());
                }
                break;
                }
                
                          
   }
}
    