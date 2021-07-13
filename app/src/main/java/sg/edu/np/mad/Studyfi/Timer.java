package sg.edu.np.mad.Studyfi;

public class Timer {
    public long amountOfTimeLeft;
    public boolean paused;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
