package by.bsuir.cinema.dao;

import by.bsuir.cinema.domain.Seat;

public interface SeatDao extends BaseDao<Seat> {

	Seat read(int row, int number);

}
