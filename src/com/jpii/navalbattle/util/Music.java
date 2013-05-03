package com.jpii.navalbattle.util;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Music {

	private URL url;
	private AudioInputStream stream;
	private AudioInputStream decodedStream;
	private AudioFormat format;
	private AudioFormat decodedFormat;
	private boolean stop;

	public Music(final URL loc) {
		this.url = loc;
		this.stop = false;
	}

	public void playLoop() {
		while (!this.stop) {
			try {
				this.stream = AudioSystem.getAudioInputStream(this.url);
				this.decodedStream = null;
				if (this.stream != null) {
					this.format = this.stream.getFormat();
					this.decodedFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							this.format.getSampleRate(), 16,
							this.format.getChannels(),
							this.format.getChannels() * 2,
							this.format.getSampleRate(), false);
					this.decodedStream = AudioSystem.getAudioInputStream(
							this.decodedFormat, this.stream);
				}
			} catch (Exception e){
			}
			SourceDataLine line = null;
			try {
				line = this.getLine(this.decodedFormat);
			} catch (LineUnavailableException lue) {
			}
			if (line != null) {
				try {
					byte[] data = new byte[4096];
					line.start();
					int nBytesRead = 0;
					while (nBytesRead != -1) {
						nBytesRead = this.decodedStream.read(data, 0,
								data.length);
						if (nBytesRead != -1) {
							line.write(data, 0, nBytesRead);
						}
						if (this.stop) {
							break;
						}
					}
					line.drain();
					line.stop();
					line.close();
					this.decodedStream.close();
					this.stream.close();
				} catch (IOException io) {
				}
			}
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

	public void stopLoop() {
		this.stop = true;
	}
}