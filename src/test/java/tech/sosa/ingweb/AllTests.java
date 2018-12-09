package tech.sosa.ingweb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.sosa.ingweb.application.movie.service.AddMovieTest;
import tech.sosa.ingweb.application.movie.service.DeleteMovieTest;
import tech.sosa.ingweb.application.movie.service.ListAllMoviesTest;
import tech.sosa.ingweb.application.movie.service.ListMovieByIdTest;
import tech.sosa.ingweb.application.movie.service.SearchMoviesTest;
import tech.sosa.ingweb.application.movie.service.UpdateMovieTest;

@RunWith(Suite.class)
@SuiteClasses({
	AddMovieTest.class,
	DeleteMovieTest.class,
	ListAllMoviesTest.class,
	ListMovieByIdTest.class,
	UpdateMovieTest.class,
	SearchMoviesTest.class
})
public class AllTests {

}
