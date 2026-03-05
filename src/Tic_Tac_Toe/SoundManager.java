package Tic_Tac_Toe;

import javax.sound.midi.*;

public class SoundManager {

    private static Synthesizer synth;
    private static MidiChannel channel;

    static {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channel = synth.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playWin() {
        playNotes(new int[]{60, 64, 67, 72}, new int[]{150, 150, 150, 1000});
    }

    public static void playTie() {
        playNotes(new int[]{55, 50}, new int[]{400, 1000});
    }

    public static void playBut() {
        playNotes(new int[]{72}, new int[]{100});
    }

    private static void playNotes(int[] notes, int[] durations) {
        new Thread(() -> {
            try {
                for (int i = 0; i < notes.length; i++) {
                    channel.noteOn(notes[i], 80);
                    Thread.sleep(durations[i]);
                    channel.noteOff(notes[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}