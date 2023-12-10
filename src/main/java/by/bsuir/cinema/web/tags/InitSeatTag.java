package by.bsuir.cinema.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.bsuir.cinema.dao.impl.SeatDaoHibernateImpl;
import by.bsuir.cinema.dao.impl.TicketsOrderDaoHibernateImpl;
import by.bsuir.cinema.domain.FilmSession;
import by.bsuir.cinema.domain.Seat;
import by.bsuir.cinema.domain.TicketsOrder;

public class InitSeatTag extends TagSupport {

	// @Autowired
	// private SeatService seatService;
	// @Autowired
	// private TicketsOrderService ticketsOrderService;

	private int row;
	private int column;
	private FilmSession filmSession;
	private boolean isStateRequired;

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setFilmSession(FilmSession filmSession) {
		this.filmSession = filmSession;
	}

	public void setIsStateRequired(boolean isStateRequired) {
		this.isStateRequired = isStateRequired;
	}

	@Override
	public int doStartTag() throws JspException {
		// SeatService seatService = new SeatServiceImpl();
		// Seat seat = seatService.readSeat(row, column);
		Seat seat = new SeatDaoHibernateImpl().read(row, column);
		if (seat != null && isStateRequired) {
			// TicketsOrderService ticketsOrderService = new TicketsOrderServiceImpl();
			// TicketsOrder ticketsOrder =
			// ticketsOrderService.readOrderWhereSeatPresent(seat, filmSession);
			TicketsOrder ticketsOrder = new TicketsOrderDaoHibernateImpl().read(seat, filmSession);
			if (ticketsOrder == null)
				seat.setState(Seat.State.FREE);
			else if (ticketsOrder.getIsPaid()) {
				seat.setState(Seat.State.OCCUPIED);
			} else {
				seat.setState(Seat.State.BOOKED);
			}
		}
		pageContext.setAttribute("seat", seat);
		return SKIP_BODY;
	}
}
