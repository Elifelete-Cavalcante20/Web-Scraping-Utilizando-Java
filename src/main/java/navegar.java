import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class navegar {
    @Test
    public void pesquisarNavegador() {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\msedgedriver.exe");
        EdgeDriver navegador = new EdgeDriver();

        navegador.get("https://www.mercadolivre.com.br/");

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement scroll = navegador.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[1]/section[2]/div[2]/section/section/div/div/div/div[1]/h2"));
        navegador.executeScript("arguments[0].scrollIntoView(true);", scroll);

        boolean continua = true;
        while (continua) {
            try {
                List<WebElement> section = navegador.findElements(By.xpath("//*[@id=\":R9ode:\"]/div[2]/div//a[contains(@class, 'poly-component__title')]"));
                for (WebElement p : section) {
                    System.out.println(p.getText());
                }
                WebElement botaoCarrosel = navegador.findElement(By.xpath("//*[@id=\":R9ode:\"]/div[2]/button[2]"));
                if (botaoCarrosel.isEnabled()) {
                    botaoCarrosel.click();
                    Thread.sleep(1500);
                } else {
                    continua = false;
                }
            } catch (Exception e) {
                continua = false;
                System.err.println("Fim do carrosel ou erro ao encontrar próxima página.");
                e.printStackTrace();
            }
        }
        navegador.close();
    }
}



