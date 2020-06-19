package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

	static public void fillForm(WebDriver driver, String dniParam, String passwordParam) {
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(dniParam);
		
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordParam);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
