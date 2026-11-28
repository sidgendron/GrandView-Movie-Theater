package org.champlain.prog2.grandviewmovietheater.Model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a screening room at GrandView Movie Theatre.
 *
 * @author Keren
 *
 * Rooms are already established and are selected when
 * a manager schedules a screening.
 */
public class ScreeningRoom {

    /**
     * Identifies the room by number, same as Screening Room ID
     */
    private final int aRoomNumber;

    /**
     * Creates a screening room.
     *
     * @param pRoomNumber the room number
     ** @throws IllegalArgumentException if the room number is not between 1 and 5
     */
    public ScreeningRoom(int pRoomNumber) {
        if (pRoomNumber < 1 || pRoomNumber > 5) {
            throw new IllegalArgumentException(
                    "Room number must be between 1 and 5.");
        }

        this.aRoomNumber = pRoomNumber;
    }

    /**
     * Gets the room number.
     *
     * @return the room number
     */
    public int getRoomNumber() {
        return this.aRoomNumber;
    }

    /**
     * Checks whether this room is available for a new screening.
     *
     * @param pScreening the screening being scheduled
     * @param pScreenings the list of existing screenings
     * @return true if there is no overlapping screening in this room
     */
    public boolean isAvailable(
            Screening pScreening, List<Screening> pScreenings) {

        if (pScreening == null || pScreenings == null) {
            throw new IllegalArgumentException(
                    "Screening and screening list cannot be null.");
        }

        // Get the requested screening's starting date and time.
        LocalDateTime requestedStart = LocalDateTime.of(
                pScreening.getScreeningDate().getScreeningDate(),
                pScreening.getStartTime());

        // Get its ending date and time.
        LocalDateTime requestedEnd = LocalDateTime.of(
                pScreening.getScreeningDate().getScreeningDate(),
                pScreening.getEndTime());

        // If the screening ends after midnight, move its end to the next day.
        if (!requestedEnd.isAfter(requestedStart)) {
            requestedEnd = requestedEnd.plusDays(1);
        }

        // Check every screening already scheduled.
        for (Screening existingScreening : pScreenings) {

            // Ignore the same screening if it is being updated.
            if (existingScreening == pScreening) {
                continue;
            }

            // Only compare screenings assigned to this room.
            if (existingScreening.getScreeningRoom().getRoomNumber()
                    == this.aRoomNumber) {

                LocalDateTime existingStart = LocalDateTime.of(
                        existingScreening.getScreeningDate().getScreeningDate(),
                        existingScreening.getStartTime());

                LocalDateTime existingEnd = LocalDateTime.of(
                        existingScreening.getScreeningDate().getScreeningDate(),
                        existingScreening.getEndTime());

                if (!existingEnd.isAfter(existingStart)) {
                    existingEnd = existingEnd.plusDays(1);
                }

                // Check whether the two screening times overlap.
                if (requestedStart.isBefore(existingEnd)
                        && existingStart.isBefore(requestedEnd)) {
                    return false;
                }
            }
        }

        // No conflicting screening was found, so yeah the room is available.
        return true;
    }
}
