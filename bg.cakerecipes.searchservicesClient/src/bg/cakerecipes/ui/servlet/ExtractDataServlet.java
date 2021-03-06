package bg.cakerecipes.ui.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.cakerecipes.drservices.dr.model.RetrievedCake;
import bg.cakerecipes.externalservices.model.ExternalCake;
import bg.cakerecipes.ui.servlet.consumers.DaoServiceConsumer;
import bg.cakerecipes.ui.servlet.consumers.DrServiceConsumer;
import bg.cakerecipes.ui.servlet.consumers.ExternalServiceConsumer;
import bg.cakerecipes.ui.servlet.consumers.WebCrawlerServiceConsumer;
import bg.cakerecipes.webcrawlerservices.crawl.model.WebCrawlerCake;

public class ExtractDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintStream out = new PrintStream(response.getOutputStream());
		DaoServiceConsumer daoConsumer = new DaoServiceConsumer();

		out.println("<br> INTEGRATING DR into DAO");
		DrServiceConsumer drConsumer = new DrServiceConsumer();
		writeDrModelIntoDao(drConsumer.getRetrievedCakes(), daoConsumer, out);

		out.println("<br> INTEGRATING External into DAO");
		ExternalServiceConsumer extConsumer = new ExternalServiceConsumer();
		writeExternalModelIntoDao(extConsumer.getExternalCakes(), daoConsumer, out);

		out.println("<br> INTEGRATING Web Crawler into DAO");
		WebCrawlerServiceConsumer crawlerConsumer = new WebCrawlerServiceConsumer();
		writeWebCrawlerModelIntoDao(crawlerConsumer.getWebCakes(), daoConsumer, out);

		out.flush();
		out.close();
	}

	private void writeWebCrawlerModelIntoDao(List<WebCrawlerCake> webCakes, DaoServiceConsumer daoConsumer,
	      PrintStream out) {
		for (WebCrawlerCake cake : webCakes) {
			out.printf("<br>Trying to write retrieved cake (name= %s )... ", cake.getName());
			boolean result = daoConsumer.writeCrawlerCake2Dao(cake);
			printResultString(out, result);
		}

		daoConsumer.displayAllDbStoreCakes(out);
	}

	private void writeDrModelIntoDao(List<RetrievedCake> retrievedCakes, DaoServiceConsumer daoConsumer, PrintStream out) {
		for (RetrievedCake cake : retrievedCakes) {
			out.printf("<br>Trying to write retrieved cake (name= %s )... ", cake.getName());
			boolean result = daoConsumer.writeDrCake2Dao(cake);
			printResultString(out, result);
		}

		daoConsumer.displayAllDbStoreCakes(out);
	}

	private void writeExternalModelIntoDao(List<ExternalCake> externalCakes, DaoServiceConsumer daoConsumer,
	      PrintStream out) {
		for (ExternalCake cake : externalCakes) {
			out.printf("<br>Trying to write retrieved cake (name= %s )... ", cake.getName());
			boolean result = daoConsumer.writeExternalCake2Dao(cake);
			printResultString(out, result);
		}

		daoConsumer.displayAllDbStoreCakes(out);
	}

	private void printResultString(PrintStream out, boolean result) {
		if (result) {
			out.println("SUCCESS");
		} else {
			out.println("FAILED");
		}
	}
}
