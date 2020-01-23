package com.searchmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name="q")
    private WebElement searchTxt;

    @FindBy(xpath ="//*[@class='search__button  js-search-button']")
    //*[@id="search_form_homepage_top"]/input[2]
    private WebElement searchBtn;

    @FindBy(xpath = "//*[@data-zci-link='videos']")
    private WebElement videosLink;

    @FindBy(xpath ="//*[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']")
    private List<WebElement> allVideos;

    public SearchPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
    public void goTo(){
        this.driver.get("https://duckduckgo.com/");
    }
    public void doSearch(String keyword){
    this.wait.until(ExpectedConditions.visibilityOf(this.searchTxt));
    this.searchTxt.sendKeys(keyword);
    this.searchBtn.click();
    }

    public void goToVideos(){
        this.wait.until(ExpectedConditions.visibilityOf(this.videosLink));
        this.videosLink.click();
    }

    public int getResult(){
        By by = By.xpath("//*[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by,0));
        System.out.println(this.allVideos.size());
        return this.allVideos.size();
    }

}
