package com.FoodSocialNetwork.app;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

class TestMain {
  public static void main(String args[]) {
    File f = new File("C:\\local\\repos\\FSN\\FoodSocialNetwork\\BackEnd\\image\\recipe0.png");
    System.out.println("Mime Type of " + f.getName() + " is " +
                         new MimetypesFileTypeMap().getContentType(f));

  }
}
