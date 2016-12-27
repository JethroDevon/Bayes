import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

class Bayes{

    boolean isNumAndClass;
    CSVReader reader;
    String nextLine[];

    ArrayList<Data> dataline = new ArrayList<Data>();

    Bayes( String _CVSdatafile, boolean _isNumAndClass){

        //this flag determines wether or not the data starts with an agent number and ends with a string representing a class
        isNumAndClass = _isNumAndClass;

        try{

            //reads csv file into filestream
            reader = new CSVReader( new FileReader( _CSVdatafile));

            //loops for each line of data cvs data
            while(( nextLine = reader.readNext()) != null){

                //creates a new temporary data object to initialise the
                //data line array with
                Data tmp = new Data();
                for( String l: nextLine){

                    //adds each substring to data class
                    tmp.addString(l);
                }

                //it is numbered and has a class type in the form of a string on the end, add those two values to the list
                //so as to allow special functionality and aid with K nearest neighbour problems
                if( _isNumAndClass && dataline.size() > 0){

                    //gets first element in list of data and add that to its
                    //item number for extra functionalities
                    tmp.setItemNum( (int) Double.parseDouble(tmp.items.get(0)));

                    //appends last item on the data list to string containing
                    //class type for extra functionalities
                    tmp.setClassString( tmp.items.get( tmp.items.size()-1));
                }

                //adds the data class to data line
                dataline.add( tmp);
            }
        }catch( IOException e){

            System.out.println("Error loading from file:" + _CSVdatafile);
        }
    }


}
