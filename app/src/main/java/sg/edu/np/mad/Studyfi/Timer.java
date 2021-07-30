package sg.edu.np.mad.Studyfi;

//Timer class
public class Timer {
    public long amountOfTimeStored;
    public boolean paused;
    public String state; // Can be Inactive, Countdown or Stopwatch

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
