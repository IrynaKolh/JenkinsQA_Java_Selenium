package school.redrover;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import school.redrover.runner.*;

import java.util.*;

public class FreestyleProjectTest extends BaseTest {
    //Navigation Test
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

    //Functional CREATE NEW FREESTYLE PROJECT JOB Test
    @Test(dependsOnMethods = "testCreateNewJobArrowIconNavigatesToNewJob")
    public void testCreateFreestyleProject() {
        final String expectedJobNameCreated = "My First Freestyle project";
        final int expectedAmountOfJobsCreated = 1;

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.name("name")).sendKeys(expectedJobNameCreated);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.id("jenkins-home-link")).click();

        List<WebElement> jobs = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));

        Assert.assertEquals(jobs.size(), expectedAmountOfJobsCreated);

        WebElement jobNameElement = jobs.get(0).findElement(By.xpath("//tr[@id='job_"+ expectedJobNameCreated + "']/td/a/span"));
        final String actualJobNameText = jobNameElement.getText();

        Assert.assertTrue(jobNameElement.isDisplayed());
        Assert.assertEquals(actualJobNameText, expectedJobNameCreated);

    }

    @Test(dependsOnMethods = "testCreateNewJobArrowIconNavigatesToNewJob")
    public void testCreateFreestyleProjectAndSaveDefaultConfig() {
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
