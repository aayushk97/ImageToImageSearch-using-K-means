public static void meanFilter_RGB(BufferedImage img, int maskSize){
      
        int outputPixels[] = new int[img.getWidth()*img.getHeight()];
        
        //image dimension
        int width = img.getWidth();
        int height = img.getHeight();
        
        //ARGB variable
        int alpha, red, green, blue;
        System.out.println("a");
        
              for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                alpha = 0;
                red = 0;
                green = 0;
                blue = 0;
                int count = 0;
                for(int r = y - (maskSize / 2); r <= y + (maskSize / 2); r++){
                    for(int c = x - (maskSize / 2); c <= x + (maskSize / 2); c++){
                        if(r < 0 || r >= height || c < 0 || c >= width){
                            /** Some portion of the mask is outside the image. */
                            continue;
                        }else{
                            int rgb =img.getRGB(c,r);
                
                            alpha += (rgb>>24)&0xff;
                            red += (rgb >> 16 ) & 0xff;
                            green += (rgb >> 8 )& 0xff;
                            blue += (rgb >> 0) & 0xff;
                            count++;
                        }
                    }
                }
                
                /** save mean value in outputPixels array */
                int p = getPixelValueFromARGBValue(alpha/count, red/count, green/count, blue/count);
                outputPixels[x+y*width] = p;
            }
        }
        
        /** Write the output pixels to the image pixels */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                img.setRGB(x, y, outputPixels[x+y*width]);
            }
        }
        
         try{
                        f=new File("D:\\Images\\Output3.jpg");
                        ImageIO.write(img, "jpg", f);
             
                    }catch(IOException e){
                        System.out.println("Error"+e);
                    }
        
       
    }
