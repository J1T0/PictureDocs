import java.util.LinkedList;

public class DocxParser {
	public static LinkedList<Line> GetLinesFromLetters(
			LinkedList<Letter> letters) {
		LinkedList<Line> lines = new LinkedList<Line>();

		LinkedList<Letter> letterCopy = new LinkedList<Letter>();
		for (int i = 0; i < letters.size(); i++) { // copies the letters to a
													// new tmp list
			Letter tmp = new Letter(letters.get(i).GetX(), letters.get(i)
					.GetY(), letters.get(i).GetWidth(), letters.get(i)
					.GetHeight(), letters.get(i).GetValue());
			letterCopy.add(tmp);
		}

		String lineVal = "";
		int lineHeight = 0;
		LinkedList<Integer> heights = new LinkedList<Integer>();
		Letter lastFound = null;
		while (letterCopy.size() > 0) {
			if (lastFound == null) { // find new line
				for (int i = 0; i < letterCopy.size(); i++) {
					if (lastFound != null) {
						if (lastFound.GetX() > letterCopy.get(i).GetX())
							lastFound = letterCopy.get(i);
					} else
						lastFound = letterCopy.get(i);
				}
				lineHeight = lastFound.GetY();
				lineVal += lastFound.GetValue();
				letterCopy.remove(lastFound);
			} else { // continue existing line
				Letter bestMatch = null;
				// finds best match in the line for next char
				for (int i = 0; i < letterCopy.size(); i++) {
					if (letterCopy.get(i).GetX() > lastFound.GetX()
							&& Math.abs(letterCopy.get(i).GetY()
									- lastFound.GetY()) < lastFound.GetHeight()) {
						if (bestMatch != null) {
							if (bestMatch.GetX() > letterCopy.get(i).GetX())
								bestMatch = letterCopy.get(i);
						} else
							bestMatch = letterCopy.get(i);
					}
				}
				if (bestMatch != null) {
					if (bestMatch.GetX() - lastFound.GetX()
							- lastFound.GetWidth() > lastFound.GetWidth() / 2)
						lineVal += " ";
					lineVal += bestMatch.GetValue();
					letterCopy.remove(bestMatch);
					lastFound = bestMatch;
				} else { // line ended
					boolean inserted = false;
					// inserts line in correct order
					for (int i = 0; i < lines.size(); i++) {
						if (heights.get(i) > lineHeight) {
							heights.add(i, lineHeight);
							lines.add(i, new Line(lineVal));
							inserted = true;
							break;
						}

					}
					if (!inserted) {
						heights.add(lineHeight);
						lines.add(new Line(lineVal));
					}
					lineVal = "";
					lastFound = null;
				}
			}

		}
		boolean inserted = false;
		// inserts line in correct order
		for (int i = 0; i < lines.size(); i++) {
			if (heights.get(i) > lineHeight) {
				heights.add(i, lineHeight);
				lines.add(i, new Line(lineVal));
				inserted = true;
				break;
			}

		}
		if (!inserted) {
			heights.add(lineHeight);
			lines.add(new Line(lineVal));
		}
		return lines;
	}

	
	

	
}
