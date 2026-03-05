package Tic_Tac_Toe;

import javax.sound.midi.*;

public class SoundManager {

    public static void playWin() {
        playNotes(new int[]{60, 64, 67, 72}, new int[]{150, 150, 150, 400});
    }

    public static void playTie() {
        playNotes(new int[]{55, 50}, new int[]{200, 400});
    }

    private static void playNotes(int[] notes, int[] durations) {
        new Thread(() -> {
            try {
                Synthesizer synth = MidiSystem.getSynthesizer();
                synth.open();
                MidiChannel channel = synth.getChannels()[0];
                for (int i = 0; i < notes.length; i++) {
                    channel.noteOn(notes[i], 80);
                    Thread.sleep(durations[i]);
                    channel.noteOff(notes[i]);
                }
                synth.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}