import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.List;

public class navegar {
    @Test
    public void pesquisarNavegador() {
        System.setProperty("webdriver.chrome.driver", "src\\drive\\msedgedriver.exe");
        EdgeDriver navegador = new EdgeDriver();

        navegador.get("https://www.mercadolivre.com.br/");

        List<WebElement> section = navegador.findElements(By.cssSelector("div[class*='poly-card']"));
        for (WebElement p : section) {
            System.out.println(p.getText());
        }
    }
}


