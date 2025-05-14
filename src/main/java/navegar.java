import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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
        ArrayList<String> produtos = new ArrayList<>();
        ArrayList<String> valores = new ArrayList<>();
        WebElement scroll = navegador.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[1]/section[2]/div[2]/section/section/div/div/div/div[1]/h2"));
        navegador.executeScript("arguments[0].scrollIntoView(true);", scroll);

        boolean continua = true;
        while (continua) {
            try {
                List<WebElement> products = navegador.findElements(By.xpath("//*[@id=\":R9ode:\"]/div[2]/div//a[contains(@class, 'poly-component__title')]"));
                List<WebElement> prices = navegador.findElements(By.xpath("//*[@id=\":R9ode:\"]//span[contains(@class, 'andes-money-amount__fraction')]"));
                for (WebElement p : products) {
                    String nomeProduto = p.getText().trim();
                    if (!nomeProduto.isEmpty() && !produtos.contains(nomeProduto)) {
                        produtos.add(nomeProduto);
                    }
                }
                for (WebElement v : prices) {
                    String valorProduto = v.getText().trim();
                    if (!valorProduto.isEmpty() && !valores.contains(valorProduto)) {
                        valores.add(valorProduto);
                    }
                }
                WebElement botaoCarrosel = navegador.findElement(By.xpath("//*[@id=\":R9ode:\"]/div[2]/button[2]"));
                if (botaoCarrosel.isEnabled()) {
                    botaoCarrosel.click();
                    System.out.println("Coletando produtos em oferta por favor, aguarde...");
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

        System.out.println("\n\nProdutos encontrados: ");
        for (int i = 0; i < produtos.size(); i++) {
            String nome = produtos.get(i);
            String preco = i < valores.size() ? valores.get(i) : "Preço não encontrado";
            System.out.println((i + 1) + " - " + nome + " | R$ " + preco);
        }
        navegador.close();
    }
}



