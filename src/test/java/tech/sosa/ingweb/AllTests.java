package tech.sosa.ingweb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.sosa.ingweb.application.actor.service.AddActorTest;
import tech.sosa.ingweb.application.actor.service.DeleteActorTest;
import tech.sosa.ingweb.application.actor.service.ListActorByIdTest;
import tech.sosa.ingweb.application.actor.service.ListMovieActorByIdTest;
import tech.sosa.ingweb.application.actor.service.SearchActorsTest;
import tech.sosa.ingweb.application.actor.service.UpdateActorTest;
import tech.sosa.ingweb.application.director.service.AddDirectorTest;
import tech.sosa.ingweb.application.director.service.DeleteDirectorTest;
import tech.sosa.ingweb.application.director.service.ListDirectorByIdTest;
import tech.sosa.ingweb.application.director.service.SearchDirectorsTest;
import tech.sosa.ingweb.application.director.service.UpdateDirectorTest;
import tech.sosa.ingweb.application.movie.service.AddMovieTest;
import tech.sosa.ingweb.application.movie.service.AssignActorTest;
import tech.sosa.ingweb.application.movie.service.AssignDirectorTest;
import tech.sosa.ingweb.application.movie.service.DeleteMovieTest;
import tech.sosa.ingweb.application.movie.service.ListActorMovieByIdTest;
import tech.sosa.ingweb.application.movie.service.ListAllActorMoviesTest;
import tech.sosa.ingweb.application.movie.service.ListAllDirectorMoviesTest;
import tech.sosa.ingweb.application.movie.service.ListDirectorMovieByIdTest;
import tech.sosa.ingweb.application.movie.service.ListMovieByIdTest;
import tech.sosa.ingweb.application.movie.service.SearchMoviesTest;
import tech.sosa.ingweb.application.movie.service.UnassignActorTest;
import tech.sosa.ingweb.application.movie.service.UnassignDirectorTest;
import tech.sosa.ingweb.application.movie.service.UpdateMovieTest;

@RunWith(Suite.class)
@SuiteClasses({
	AddMovieTest.class,
	AssignActorTest.class,
	AssignDirectorTest.class,
	DeleteMovieTest.class,
	ListActorMovieByIdTest.class,
	ListAllActorMoviesTest.class,
	ListAllDirectorMoviesTest.class,
	ListDirectorMovieByIdTest.class,
	ListMovieByIdTest.class,
	UpdateMovieTest.class,
	SearchMoviesTest.class,
	UnassignActorTest.class,
	UnassignDirectorTest.class,
	UpdateMovieTest.class,
	AddDirectorTest.class,
	DeleteDirectorTest.class,
	ListDirectorByIdTest.class,
	SearchDirectorsTest.class,
	UpdateDirectorTest.class,
	AddActorTest.class,
	DeleteActorTest.class,
	ListActorByIdTest.class,
	ListMovieActorByIdTest.class,
	SearchActorsTest.class,
	UpdateActorTest.class
})
public class AllTests {

}
