package school.redrover;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import school.redrover.runner.*;

public class FreestyleProjectTest extends BaseTest {
    @Test
    public void testCreateNewJobArrowIconNavigatesToNewJob() {
        String expectedURL = ProjectUtils.getBaseUrl() + "/newJob";
        String expectedTitle = "New Item [Jenkins]";

        String oldUrl = getDriver().getCurrentUrl();
        String oldTitle = getDriver().getTitle();

        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class='trailing-icon']")).click();

        String newUrl = getDriver().getCurrentUrl();
        String newTitle = getDriver().getTitle();

        Assert.assertNotEquals(newUrl, oldUrl);
        Assert.assertNotEquals(newTitle, oldTitle);
        Assert.assertEquals(newUrl, expectedURL);
        Assert.assertEquals(newTitle, expectedTitle);
    }

    @Test(dependsOnMethods = "testCreateNewJobArrowIconNavigatesToNewJob")
    public void testCreateFreestyleProject() {
        String expectedResult = "My First Freestyle project";

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("My First Freestyle project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
