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
        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class='trailing-icon']")).click();


    }
}
