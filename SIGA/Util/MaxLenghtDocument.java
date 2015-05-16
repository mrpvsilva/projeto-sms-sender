package Util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLenghtDocument extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int max;

	public MaxLenghtDocument(int maxLength) {
		this.max = maxLength;
	}

	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() > max)
			java.awt.Toolkit.getDefaultToolkit().beep();
		else
			super.insertString(offset, str, a);
	}

}
