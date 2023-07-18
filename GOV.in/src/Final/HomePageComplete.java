package Final;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageComplete {
	
private WebDriver driver;
	
	@BeforeClass
	public void launch()
	{
		  System.setProperty("webdriver.chrome.driver","/Users/Clicker/Documents/Apurva 2023/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void close()
	{
		driver.quit();
	}
	
	//****** HEADER - HOME PAGE ******** START
	
	@Test (priority = 1)
	public void HomePage1Title()
	{
		driver.get("https://indianculture.gov.in");

       
        String pageTitle = driver.getTitle();

        assert pageTitle.contains("INDIAN CULTURE");
        
        System.out.println("---Title test ends---");
	}
	
	@Test (priority = 2)
	public void HomePage2Search() throws InterruptedException
	{
		 	  
		 

	        // Enter search keyword
	        WebElement searchInput = driver.findElement(By.id("edit-search-api-fulltext"));
	        searchInput.sendKeys("music");

	        // Submit the search form
	        WebElement searchButton = driver.findElement(By.className("form-submit"));
	        searchButton.click();
	        
	        Thread.sleep(2000);
	        // Verify search results
	       
	        WebElement searchResults = driver.findElement(By.cssSelector("div#block-filters"));
	     // WebElement searchResults = driver.findElement(By.xpath("//*[@id=\'block-filters\']/h3/text()"));
	     // String result = searchResults.getText();

	        // Verify search results are displayed
	        Assert.assertTrue(searchResults.isDisplayed());
	        System.out.println("---Search test ends---");
	}
	
	
	@Test (priority = 4)
	public void HomePage4GoI () throws InterruptedException
	{
	        // Navigate to the page that contains the link
	    	driver.get("https://indianculture.gov.in");

	        // Get the current window handle
	        String currentWindowHandle = driver.getWindowHandle();

	        // Click on the link
	        WebElement GoI = driver.findElement(By.cssSelector("a[href='https://www.india.gov.in/']"));
			GoI.click();
			Thread.sleep(2000);

	        // Get all window handles
	        Set<String> windowHandles = driver.getWindowHandles();

	        // Find the new tab window handle
	        String newTabWindowHandle = "";
	        for (String handle : windowHandles) {
	            if (!handle.equals(currentWindowHandle)) {
	                newTabWindowHandle = handle;
	                break;
	            }
	        }

	        // Switch to the new tab
	        driver.switchTo().window(newTabWindowHandle);

	        // Verify if the new tab URL is not equal to the original tab URL
	        String originalURL = driver.switchTo().window(currentWindowHandle).getCurrentUrl();
	        String newTabURL = driver.switchTo().window(newTabWindowHandle).getCurrentUrl();
	        Assert.assertNotEquals(newTabURL, originalURL, "The link does not open in a new tab by default");

	        // Close the new tab
	        //driver.close();
	        System.out.println("---GoI test ends---");
	    }
	
	@Test (priority = 5)
	public void HomePage5Skip () throws InterruptedException
	{
    	driver.get("https://indianculture.gov.in");
    	
    	WebElement Skip = driver.findElement(By.id("skipdiv"));
			Skip.click();
			Thread.sleep(2000);
		WebElement SkiptoCat = driver.findElement(By.cssSelector("div.section-header"));
		String SkiptoCattext = SkiptoCat.getText();
		Assert.assertEquals(SkiptoCattext, "Categories");
		
		System.out.println("---Skip test ends---");
	}
	
	@Test (priority = 6)
    public void HomePage6MenuButton() throws InterruptedException {
        // Navigate to the web page
    	driver.get("https://indianculture.gov.in");

        // Find the menu button element
        WebElement menuButton = driver.findElement(By.xpath("//*[@id='header']/div/div/nav/div[5]/button"));
        
        Thread.sleep(1000);
        //Verify the menuicon1
//        WebElement menuicon1 = driver.findElement(By.cssSelector("div.pull-right"));
//        Assert.assertTrue(menuicon1.isDisplayed());
        
        // Click on the menu button
        menuButton.click();

        // Wait for the menu bar to appear (You may need to add an appropriate wait here)
        Thread.sleep(1000);
        // Verify if the menu bar is displayed
        WebElement menuBar = driver.findElement(By.id("navbarSupportedContent20"));
        Assert.assertTrue(menuBar.isDisplayed(), "The menu bar is not displayed after clicking the menu button");
        
        //Verify the menuicon2
        WebElement menuicon2 = driver.findElement(By.xpath("//*[@id=\'header\']/div/div/nav/div[5]/button/div"));
    	Assert.assertTrue(menuicon2.isDisplayed());
        
        // Verify if the menu items are listed in the menu bar
        WebElement menuItem1 = menuBar.findElement(By.cssSelector("#block-nvlicollections > ul > li:nth-child(1) > a"));
        Assert.assertTrue(menuItem1.isDisplayed(), "Menu item 1 is not displayed in the menu bar");
        
        //Verify is the menu bar disappears on second click
        menuButton.click();
        Assert.assertTrue(menuBar.isDisplayed(), "The menu bar did not close after clicking the menu button again.");
        System.out.println("---Menu button test ends---");
        }
	
	
	//************* SLIDE SHOW HOMEPAGE *********** START
	 @Test (priority = 7)
	    public void testImageSlideshow() throws InterruptedException 
	    {

     // Navigate to the website
     driver.get("https://indianculture.gov.in/");
 

     // Wait for the image slider to load (you may need to adjust the wait time)
     try {
         Thread.sleep(2000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }

     // Find the image slider container element
     WebElement imageSlider = driver.findElement(By.cssSelector("div.field-content"));

     ArrayList<String> tE = new ArrayList<String>();
     int j=0;
     for(int i=0; i<40; i++)
     {
     	
     	 driver.findElement(By.xpath("//div[@id='slick-views-background-slider-view-block-1-1']/nav/button[2]")).click();
     	
     // Find all the image elements within the image slider
     //java.util.List<WebElement> images = imageSlider.findElements(By.tagName("img"));
     	List<WebElement> images = imageSlider.findElements(By.tagName("img"));
     // Verify if images are appearing on the image slider
//     if (images.size() > 0) {
//         System.out.println("Images are appearing on the image slider.");
//     } else {
//         System.out.println("No images are appearing on the image slider.");
//     }
     	Thread.sleep(1000);
     	 Assert.assertFalse(images.isEmpty(), "No images found in the slideshow");
     
     	 Thread.sleep(500);
          // Find the title element for the image
          WebElement titleElement = driver.findElement(By.xpath("//*[@id=\'slick-views-background-slider-view-block-1-1-slider\']/div"));
          String temptE = titleElement.findElement(By.tagName("div")).getText();
          System.out.println(temptE);
         // tE.add(temptE);
          
          
          // Verify if the title is present
          Assert.assertTrue(titleElement.isDisplayed(), "Title is not displayed for the image");
     
     }
     
     System.out.println("Total images on image slider =" + j); 
     
     System.out.println("---Slideshow test ends---");
     
}
	 
	 //********** CATEGORY HOME PAGE ************ START
	 @Test (priority = 8)
	    public void HomePage8testCategorySlider() throws InterruptedException {
	    	
	    	WebElement categorysection = driver.findElement(By.className("views-element-container"));
	    	WebElement rightButton = categorysection.findElement(By.xpath("(//button[@type='button'])[12]"));

	    	 
	    	int i=0;
	    	ArrayList<String> categorynames = new ArrayList<String>();
				for (int j=5; j>=0; j--)
				{
					Thread.sleep(1000);	
								
				List<WebElement> categories = driver.findElements(By.className("pad15"));
				
	    		 for (WebElement category : categories) 
				{
		             String categoryText = category.getText();
		             categorynames.add(categoryText);
		             
		          
				}
	    		 rightButton.click();
	    		
				}
				 Set<String> uniqueElements = new HashSet<>(categorynames);
		        for (String element : uniqueElements) 
		        {
		            System.out.println(element);
		             i++;
	             }
		        	
	    		 
				
				System.out.println(i);
				System.out.println("Categories available:" + i);
				
				System.out.println("---Category test ends---");
	    }
	 
	 //********* FREEDOM ARCHIVE HOME PAGE ********* START
	 	 @Test (priority = 9)
	  public void HomePage9freedomarchives () {
		 driver.get("https://indianculture.gov.in/");
	  WebElement section = driver.findElement(By.id("block-sidebarblock-2"));
	  Assert.assertTrue(section.isDisplayed(), "Freedom Archive section is missing");

	  
	  WebElement header = section.findElement(By.className("section-header"));
	  String title = header.findElement(By.tagName("h3")).getText();
	  Assert.assertEquals(title, "Freedom Archive");
	  
	  WebElement image = section.findElement(By.tagName("img"));
	  Assert.assertTrue(image.isDisplayed(), "Image is absent");
	  
	  image.click();
	  String url = driver.getCurrentUrl();
	  System.out.println("On clicking Freedom Archives, this url opens:"+url);
	  Assert.assertEquals(url, "https://indianculture.gov.in/freedom-archive");
	  
	  System.out.println("---Freedom Archive test ends---");
	  }
	 
	 	 //************ DID YOU KNOW ********* START
	 	@Test (priority = 10)
		public void HomePage10didyouknow () {
			 driver.get("https://indianculture.gov.in/");
			 
		WebElement section = driver.findElement(By.id("block-eventname-2"));
		Assert.assertTrue(section.isDisplayed(), "Did you know section is missing");
		
		WebElement header = section.findElement(By.tagName("h3"));
		  String title = header.getText();
		  Assert.assertEquals(title, "Did You Know?");
		  
		  WebElement image = driver.findElement(By.xpath("//*[@id=\"block-views-block-did-you-know-block-1\"]/div/div/div/div[1]/span/div/div[2]/img"));
		 // WebElement img = image.findElement(By.tagName("img"));
		  Assert.assertTrue(image.isDisplayed(), "Image is absent");
		  
//		  image.click();
//		  String url = driver.getCurrentUrl();
//		  System.out.println("On clicking Did you know?, this url opens:"+url);
//		  Assert.assertEquals(url, "https://indianculture.gov.in/freedom-archive");
//		  Assert.assertEquals(driver.getTitle(), "Did you Know?");
		  System.out.println("---Did you know test ends---");
				
		}
	 	
	 	//******** STORIES HOME PAGE******** START
	 	@Test (priority = 11)
	    public void HomePage11testStorySlider() throws InterruptedException {
	    	
	    	WebElement storysection = driver.findElement(By.id("block-views-block-story-view-block-1"));
	    	WebElement rightButton = storysection.findElement(By.xpath("//*[@id=\"slick-views-story-view-block-1-3\"]/nav/button[2]"));

	    	 
	    	int i=0;
	    	ArrayList<String> storynames = new ArrayList<String>();
				for (int j=20; j>=0; j--)
				{
					
								
				List<WebElement> stories = storysection.findElements(By.className("storybox-title1"));
				
	    		 for (WebElement story : stories) 
				{
		             String storyText = story.getText();
		             storynames.add(storyText);
		             
//		             if(categoryText.length()>0)
//		             {
//		            	 
//		            	 System.out.println(categoryText);
//		            	 
//		            	 i++;		            	 
//				     }
				}
	    		 rightButton.click();
	    		
				}
				 Set<String> uniqueElements = new HashSet<>(storynames);
				 
				
					for (String element : uniqueElements) 
					{
					    System.out.println(element);
					   
					    
					     i++;
					 }
				
				 
		        System.out.println("STORIES AVAILABLE: " + i);
		        System.out.println("---Stories test ends---");
	    }
	 
//*********** EXTERNAL LINKS HOME PAGE ******************* START
	 	@Test (priority = 12)
	 	public void externallinks () throws InterruptedException {
	 		
	 	    	
	 	    	WebElement ELsection = driver.findElement(By.id("mocexternal"));
	 	    	WebElement rightButton = ELsection.findElement(By.xpath(".//button[@aria-label='Next']"));
	 	    	
	 	    	 
	 	    	int i=0;
	 	    	ArrayList<String> ELnames = new ArrayList<String>();
	 				for (int j=5; j>=0; j--)
	 				{
	 					
	 					Thread.sleep(1000);	
	 					
	 					List<WebElement> unit = ELsection.findElements(By.className("slick-active"));
	 					
	 					for (WebElement EL : unit) 
	 					{
	 						//Validate moc title
	 						
	 						WebElement title = EL.findElement(By.tagName("h3"));
	 						
	 			             String ELText = title.getText();
	 			             ELnames.add(ELText);
	 			             
//	 			             if(categoryText.length()>0)
//	 			             {
//	 			            	 
//	 			            	 System.out.println(categoryText);
//	 			            	 
//	 			            	 i++;		            	 
//	 					     }
	 			             
	 			             //Validate moc image
	 			             WebElement ELicon = EL.findElement(By.tagName("img"));	
	 			             Assert.assertTrue(ELicon.isDisplayed(), "Thumbnail is missing.");
	 					}
	 		    		 rightButton.click();
	 	    		
	 				}
	 				
	 				 Set<String> uniqueElements = new HashSet<>(ELnames);
	 		        for (String element : uniqueElements) 
	 		        {
	 		            System.out.println(element);
	 		             i++;
	 	             }
	 		        	
	 	    		 
	 				
	 				System.out.println("External links AVAILABLE: " + i);
	 				System.out.println("---External Link test ends---");
	 		
	 	}	
	 	
	 	
//************** MOC ORG HOME PAGE************* START
	 	@Test (priority = 13)
	    public void testMOCSlider() throws InterruptedException {
	    	
	    	WebElement MOCsection = driver.findElement(By.xpath("//*[@id=\'slick-views-all-moc-organization-block-1-5-slider\']/div/div"));
	    	WebElement rightButton = MOCsection.findElement(By.xpath("//*[@id=\'slick-views-all-moc-organization-block-1-5\']/nav/button[2]"));
	    	
	    	 
	    	int i=0;
	    	ArrayList<String> MOCnames = new ArrayList<String>();
				for (int j=15; j>=0; j--)
				{
					
					Thread.sleep(1000);	
					
					List<WebElement> unit = MOCsection.findElements(By.className("slick-active"));
					
					for (WebElement MOC : unit) 
					{
						//Validate moc title
						
						WebElement title = MOC.findElement(By.className("externallink"));
						
			             String MOCText = title.getText();
			             MOCnames.add(MOCText);
			             
//			             if(categoryText.length()>0)
//			             {
//			            	 
//			            	 System.out.println(categoryText);
//			            	 
//			            	 i++;		            	 
//					     }
			             
			             //Validate moc image
			             WebElement MOCicon = MOC.findElement(By.tagName("img"));	
			             Assert.assertTrue(MOCicon.isDisplayed(), "Thumbnail is missing.");
					}
		    		 rightButton.click();
	    		
				}
				
				 Set<String> uniqueElements = new HashSet<>(MOCnames);
		        for (String element : uniqueElements) 
		        {
		            System.out.println(element);
		             i++;
	             }
		        	
	    		 
				
				System.out.println("MOC ORGANISATIONS AVAILABLE: " + i);
				System.out.println("---MoC test ends---");
	    }
	 	
	 	//***************** PARTNERS HOMEPAGE********** START
	 	@Test (priority = 14)
		  public void HomePage14partners () {
			  driver.get("https://indianculture.gov.in/");
			  
			  WebElement section = driver.findElement(By.xpath("//*[@id='block-partnerinstitutions']/div/div[3]"));
				Assert.assertTrue(section.isDisplayed(), "Partners section is missing");
				
				WebElement header = section.findElement(By.className("section-header"));
				  String title = header.getText();
				  Assert.assertEquals(title, "Partners");
				  
				// partners individual icon test not written as one is image and another is an icon and text
				  
				  System.out.println("---Partner test ends---");
		  }
	 	
	 	//****************** FOOTER HOMEPAGE ******** START
	 	
	 	 @Test (priority = 15)
		  public void footer () throws InterruptedException {
			 driver.get("https://indianculture.gov.in/");
			 
//			 System.out.println(driver.findElements(By.tagName("a")).size());
			 

				WebElement footerdriver = driver.findElement(By.className("section-md-t3"));// Limiting webdriver scope
				
//				System.out.println(footerdriver.findElements(By.tagName("a")).size());
				
				//Quick Links
				
				WebElement quicklinks = footerdriver.findElement(By.id("block-policies"));
				String qltitle = quicklinks.findElement(By.id("block-policies-menu")).getText();
				
//				System.out.println(quicklinks.findElements(By.tagName("a")).size());
				Assert.assertEquals(qltitle, "Quick Links");
				
				//Indian Culture App
				
				WebElement icapp = footerdriver.findElement(By.id("block-fottterappblock"));
				String icapptitle = icapp.findElement(By.tagName("h2")).getText();
				
//				System.out.println(icapp.findElements(By.tagName("a")).size());
				Assert.assertEquals(icapptitle, "Indian Culture App");
				
				//title of each linktext
				ArrayList <String> textarray = new ArrayList<String>();
				List<WebElement> textlist = quicklinks.findElements(By.tagName("a"));
			int j=0;
			for (WebElement textname : textlist)
			{
				String text = textname.getText();
				textarray.add(text);
			  System.out.println(text);
			  j++;
			}
			System.out.println("Total linkstext:" + j)	;
			
				//click on each link and check if the pages are opening-
				for(int i=1;i<quicklinks.findElements(By.tagName("a")).size();i++)
				{
					
					String clickonlinkTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
					
					footerdriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
					Thread.sleep(2000);
					
				}// opens all the tabs
				Set<String> abc=driver.getWindowHandles();//4
				Iterator<String> it=abc.iterator();
				
				ArrayList <String> pagetitlearray = new ArrayList<String>();
				
				
				 int k = 0;
				 driver.switchTo().window(it.next());
				while(it.hasNext())
				{
					
				   driver.switchTo().window(it.next());
				   pagetitlearray.add(driver.getTitle());
				    System.out.println(driver.getTitle());
				    k++;
				    			
				}
				System.out.println("Total urls:" + k);
				
				Assert.assertEquals(j,k, "Number of links are not matching with the number of urls opened.");
					
//				for (int i=0; i<footerdriver.findElements(By.tagName("a")).size(); i++)
////				{
//					boolean containsAll;
//					if (containsAll = pagetitlearray.containsAll(textarray))
//					{
//						System.out.println("Does list1 contain all elements of list2? " + containsAll);
//					}
//					
//					else
//					{
//						System.out.println("Title is not matching ");
//					}
////					
//				}
				
//					for (int i=0; i<footerdriver.findElements(By.tagName("a")).size(); i++)
////						{
//						 if (!list1.get(i).equals(list2.get(i))) {
//			                    areEqual = false;
//			                    break;
//		  				}
//				
				System.out.println("---Footer test ends---");
		  }
}
