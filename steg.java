import java.io.FileOutputStream;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

class steg {
  // Setup variables for file locations and file size
  private String og_file_loc;
  private String hide_file_loc;
  private int hide_file_point;
  private String outName;
  private String key;

  public steg(String og_file_loc, String hide_file_loc, int hide_file_point, String key){
    this.og_file_loc = og_file_loc;
    this.hide_file_loc = hide_file_loc;
    this.hide_file_point = hide_file_point;
    this.outName = "";
    this.key = key;
  }

  //In the case a file needs to be extracted
  public steg(String hide_file_loc, int hide_file_point, String outName, String key){
    this.og_file_loc = "";
    this.hide_file_loc = hide_file_loc;
    this.hide_file_point = hide_file_point;
    this.key = key;
    this.outName = outName;
  }

  public int hide_file(){
    Path hideFile = Paths.get(hide_file_loc); //Get the file that has to be hidden
    Path ogFile = Paths.get(og_file_loc);
    try{
      byte[] fData = Files.readAllBytes(hideFile);
      byte[] ogData = Files.readAllBytes(ogFile);
      xorData(fData);
      FileOutputStream fOut = new FileOutputStream(og_file_loc,true); //Append mode to make sure we don't overwrite the entire image
      hide_file_point = ogData.length;
      fOut.write(fData);
      fOut.close();
    }
    catch(Exception e){
      e.printStackTrace(); //Print out the error if one is caught
    }
    return hide_file_point;
  }

  public void extract_file(){
    Path hideFile = Paths.get(hide_file_loc); //This file has another file hidden inside
    try{
      byte[] fData = Files.readAllBytes(hideFile);
      xorData(fData); //Unencrypt the data
      byte[] hideFileData = Arrays.copyOfRange(fData,hide_file_point,fData.length); //Contains hidden file data
      FileOutputStream fOut = new FileOutputStream(outName); 
      fOut.write(hideFileData);
      fOut.close();
    }
    catch(Exception e){
      e.printStackTrace(); //Print out the error if one is caught
    }
  }

  //Simple Xor alogrithm using a user provided key 
  private byte[] xorData(byte[] arr){
    int[] keyVals = new int[key.length()];
    for(int i = 0; i < keyVals.length; i++){
      keyVals[i] = (int)key.charAt(i);
    }
    for(int i = 0; i < arr.length; i++){
      for(int val: keyVals){ //Encrypt using key
        arr[i] = (byte)((int)arr[i] ^ val); 
      }
    }
    return arr;
  }
}