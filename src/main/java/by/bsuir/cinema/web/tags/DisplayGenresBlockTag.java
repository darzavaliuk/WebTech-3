package by.bsuir.cinema.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsuir.cinema.dao.impl.GenreDaoHibernateImpl;
import by.bsuir.cinema.domain.Genre;

public class DisplayGenresBlockTag extends TagSupport {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public int doStartTag() throws JspException {
		List<Genre> genres = new GenreDaoHibernateImpl().readAll("genreName");
		StringBuilder genresBlock = new StringBuilder();
		genresBlock.append("<h5>Choose genre:</h5>");
		for (Genre g : genres) {
			genresBlock
			.append("<hr>")
			.append("<a href=\"/cinema/newapp/user/chosenGenreFilms?user_chosen_genre_id=")
			.append(g.getId())
			.append("\">")
			.append(g.getGenreName())
			.append("</a>");
		}
		JspWriter out = pageContext.getOut();
		try {
			out.write(genresBlock.toString());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		return SKIP_BODY;
	}
}