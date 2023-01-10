/**
 * 
 */
package SeleniumComplete;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mani 10-Jan-2023
 * 
 */
public class Test {
	
	public static void createList(WebDriver driver , String lstName)
	{
		//enter list name
		driver.findElement(By.className("list-name-input")).sendKeys(lstName);
		//click on add list
		driver.findElement(By.xpath("//input[@value='Add list']")).click();
		
	}
	
	public static void main(String[] args) throws InterruptedException

	{
		String boardName = "Test";
		String lst1 = "List A";
		String lst2 = "List B";
		String card1 = "Card 1";
		
		//launching chrome driver
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Entering the URL
		driver.get("https://trello.com/en");
		
		//Maximizing the window
		driver.manage().window().maximize();
		
		//Logging in
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		//Enter user name
		driver.findElement(By.id("user")).sendKeys("savithrimani113@gmail.com");
		driver.findElement(By.id("login")).click();   // Click on continue
		
		// Explicit wait : untill element is seen
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		
		//Enter password
		driver.findElement(By.id("password")).sendKeys("Trello#113");
		//Click on Login
		driver.findElement(By.xpath("//*[text()='Log in']")).click();
		
		
		// Creating new board
		driver.findElement(By.xpath("//span[text()='Create new board']")).click();
		driver.findElement(By.xpath("//input[@data-testid='create-board-title-input']")).sendKeys(boardName);
		driver.findElement(By.xpath("//button[text()='Create']")).click();
				
		
		// Creating List A
		//click on add list
		//driver.findElement(By.className("open-add-list js-open-add-list")).click();
		createList(driver , lst1);
		// Creating List B
		createList(driver , lst2);
		
		
		// Adding card in List A
		driver.findElement(By.xpath("//textarea[text()='"+lst1+"']//parent::div//parent::div//span[text()='Add a card']")).click();
		//Enter card name
		driver.findElement(By.xpath("//*[contains(@class,'list-card-composer-textarea')]")).sendKeys(card1);
		//click on 'Add Card'
		driver.findElement(By.xpath("//input[@value='Add card']")).click();
		
		
		//DRAG and DROP the card
		Actions action = new Actions(driver);
		
		WebElement drag = driver.findElement(By.xpath("//span[text()='"+card1+"']"));
		WebElement drop = driver.findElement(By.xpath("//textarea[text()='"+lst2+"']"));
		action.dragAndDrop(drag, drop).perform();
		
		//Thread.sleep(2000);
		
		//X and Y coordinates of the card
		WebElement dropLoc = driver.findElement(By.xpath("//span[text()='Card 1']"));
		System.out.println("The co-ordinates are :");
		System.out.println("X - axis :"+dropLoc.getLocation().getX());
		System.out.println("Y - axis :"+dropLoc.getLocation().getY());
		
		
		
		System.out.println("COMPLETED");
		driver.close();	
				
				
	}

}
