package tech.sosa.ingweb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.sosa.ingweb.application.actor.service.AddActorTest;
import tech.sosa.ingweb.application.actor.service.DeleteActorTest;
import tech.sosa.ingweb.application.actor.service.ListActorByIdTest;
import tech.sosa.ingweb.application.actor.service.SearchActorsTest;
import tech.sosa.ingweb.application.actor.service.UpdateActorTest;
import tech.sosa.ingweb.application.director.service.AddDirectorTest;
import tech.sosa.ingweb.application.director.service.DeleteDirectorTest;
import tech.sosa.ingweb.application.director.service.ListDirectorByIdTest;
import tech.sosa.ingweb.application.director.service.SearchDirectorsTest;
import tech.sosa.ingweb.application.director.service.UpdateDirectorTest;
import tech.sosa.ingweb.application.movie.service.AddMovieTest;
import tech.sosa.ingweb.application.movie.service.DeleteMovieTest;
import tech.sosa.ingweb.application.movie.service.ListMovieByIdTest;
import tech.sosa.ingweb.application.movie.service.SearchMoviesTest;
import tech.sosa.ingweb.application.movie.service.UpdateMovieTest;

@RunWith(Suite.class)
@SuiteClasses({
	AddMovieTest.class,
	DeleteMovieTest.class,
	ListMovieByIdTest.class,
	UpdateMovieTest.class,
	SearchMoviesTest.class,
	AddDirectorTest.class,
	ListDirectorByIdTest.class,
	UpdateDirectorTest.class,
	DeleteDirectorTest.class,
	SearchDirectorsTest.class,
	AddActorTest.class,
	DeleteActorTest.class,
	ListActorByIdTest.class,
	UpdateActorTest.class,
	SearchActorsTest.class
})
public class AllTests {

}
