import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteHtmlTest extends TestCase {
	private String[] tokens = { "<html", "<head", "</head", "<body", "<table",
			"<tr", "<td", "1", "</td", "<td", "2", "</td", "</tr", "<tr",
			"<td", "3", "</td", "<td", "4", "</td", "</tr", "</table",
			"</body", "</html" };

	private int n = 0; // index of next expected token

	// called for each token found in html file
	private void gotToken(String token) {
		// try to match against current expected token
		if (n >= tokens.length) {
			return; // already found everything we were looking for
		}
		String tok = tokens[n];
		if (tok.charAt(0) == '<') {
			if (token.toLowerCase().startsWith(tok)) {
				// found a match (ignores possible parameters in html
				// tag, so this is only a partial check
				n++; // matched, so now look for next token
				return;
			}
		} else if (token.trim().equals(tok)) {
			n++; // matched expected data
			return;
		}
	}

	public final void test() {
		String[] args = { }; // do not pass args to WriteHtml.
		WriteHtml.main(args);
		// now, read the output file and see if it has the correct
		// data. We could do this with the CSV reader code, but
		// that would give some hints we'd rather not publish within
		// the code for this task.
		BufferedReader input;
		try {
			Pattern tag = Pattern.compile("<.*?>", Pattern.DOTALL);
			input = new BufferedReader(new FileReader("output.html"));
			StringBuilder stringBuilder = new StringBuilder();
			int numRead;
			char[] buf = new char[1024];
			while ((numRead = input.read(buf)) != -1) {
				stringBuilder.append(buf, 0, numRead);
			}
			String html = stringBuilder.toString();
			Matcher m = tag.matcher(html);
			int prev = 0;
			while (m.find()) {
				gotToken(html.substring(prev, m.start()));
				prev = m.end();
				gotToken(html.substring(m.start(), prev));
			}
			// see if we matched everything
			if (n < tokens.length) {
				fail("html output missing " + tokens[n]);
			}
		} catch (FileNotFoundException ex) {
			fail("Expected to find and open output.html");
		} catch (IOException e) {
			fail("Unexpected IOException");
		}
	}

}
