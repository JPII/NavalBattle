package com.jpii.eightbitsound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundMan {
	public static void playSound(StepList sound,double duration) {
		float rate = 44100f;
		AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED,rate,Short.SIZE,1,(Short.SIZE / 8) * 1,rate,true);
		ByteBuffer data = ByteBuffer.allocate((((int) Math.ceil(rate * duration)) * 1) * (Short.SIZE / 8));
		for (int steps = 0; steps < (int) Math.ceil(rate * duration) && steps < sound.size(); steps++) {
			data.putShort((short)(sound.getStep(steps) * 100000));
		}
		AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(data.array()), format, ((int) Math.ceil(rate * duration))*2);
		Clip cp = null;
		try {
			cp = AudioSystem.getClip();
			cp.open(stream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cp.start();
		cp.drain();
	}
	
	public static boolean exportSoundFile(StepList sound, double duration, SoundFileFormat fformat, String filename) {
		float rate = 44100f;
		AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED,rate,Short.SIZE,1,(Short.SIZE / 8) * 1,rate,true);
		ByteBuffer data = ByteBuffer.allocate((((int) Math.ceil(rate * duration)) * 1) * (Short.SIZE / 8));
		for (int steps = 0; steps < (int) Math.ceil(rate * duration) && steps < sound.size(); steps++) {
			data.putShort((short)(sound.getStep(steps) * 100000));
		}
		AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(data.array()), format, ((int) Math.ceil(rate * duration))*2);
		File outputAudio = new File(filename);
		try
		{
			outputAudio.createNewFile();
		}
		catch (Throwable thrw) {
			
		}
		try {
			if (fformat == SoundFileFormat.AIFC) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFC)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AIFC, outputAudio);
			}
			else if (fformat == SoundFileFormat.AIFF) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AIFF, outputAudio);
			}
			else if (fformat == SoundFileFormat.AU) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AU)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AU, outputAudio);
			}
			else if (fformat == SoundFileFormat.B8S) {
				return SoundMan.saveStepSound(sound, 5, filename);
			}
			else if (fformat == SoundFileFormat.SND) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.SND)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.SND, outputAudio);
			}
			else if (fformat == SoundFileFormat.WAV) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputAudio);
			}
			else {
				return false;
			}
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static double truncate(double d, int digits) {
		int multiplier = (int)Math.pow(10, digits);
		long y = (long) (d * multiplier);
        return (double) y / multiplier;
	}
	
	public static boolean saveStepSound(StepList steps,int quality, String filename) {
		try {
			File f = new File(filename);
			f.createNewFile();
		}
		catch (Throwable e) {
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write("#v0.0.1\n\r");
			writer.write("#8bit sound file\n\r");
			writer.write("#Quality:" + quality + "\n\r");
			String contents = "";
			for (int x = 0; x < steps.size(); x++) {
				double rd = steps.getStep(x);
				if (quality <= 0) {
					int ded = (int)rd;
					writer.write(ded + ",");
				}
				else {
					rd = truncate(rd,quality);
					writer.write(rd + ",");
				}
			}
			writer.write(contents);
			writer.close();
			return true;
		} catch (Throwable e) {
		}
		return false;
	}
	
	public static StepList loadStepSound(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String current = "";
			
			StepList list = new StepList();
			while ((current = reader.readLine()) != null) {
				if (!current.replace(" ","").startsWith("#")) {
					String[] codecs = current.split(",");
					for (int c = 0; c < codecs.length; c++) {
						try {
							list.addStep(Double.parseDouble(codecs[c]));
						}
						catch (Throwable thrw) {
							
						}
					}
				}
			}
			return list;
		}
		catch (Throwable e) {
			return null;
		}
	}
}
