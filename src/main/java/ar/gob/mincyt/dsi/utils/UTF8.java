package ar.gob.mincyt.dsi.utils;

public class UTF8 {

	/**
	 * 
	 * Removes invalid utf8 sequences 
	 * 
	 * @param input
	 * @param output
	 * @return
	 */
	public static int cleaner(byte[] input, byte[] output) {
		int output_len = 0;
		
		for (int i = 0; i < input.length; ++i) {
			long remain = input.length - i;

			/* ASCII */
			if (input[i] == '\t' || input[i] == '\r' || input[i] == '\n'
					|| (input[i] >= ' ' && input[i] <= 127)) {
				output[output_len++] = input[i];
			}
			/* 2-byte sequence */
			else if (remain >= 2 && (input[i] & 0xe0) == 0xc0
					&& (input[i + 1] & 0xc0) == 0x80) {
				output[output_len++] = input[i++];
				output[output_len++] = input[i];
			}
			/* 3-byte sequence */
			else if (remain >= 3 && (input[i] & 0xf0) == 0xe0
					&& (input[i + 1] & 0xc0) == 0x80
					&& (input[i + 2] & 0xc0) == 0x80) {
				output[output_len++] = input[i++];
				output[output_len++] = input[i++];
				output[output_len++] = input[i];
			}
			/* 4-byte sequence */
			else if (remain >= 4 && (input[i] & 0xf8) == 0xf0
					&& (input[i + 1] & 0xc0) == 0x80
					&& (input[i + 2] & 0xc0) == 0x80
					&& (input[i + 3] & 0xc0) == 0x80) {
				output[output_len++] = input[i++];
				output[output_len++] = input[i++];
				output[output_len++] = input[i++];
				output[output_len++] = input[i];
			}
		}

		return output_len;
	}
}
