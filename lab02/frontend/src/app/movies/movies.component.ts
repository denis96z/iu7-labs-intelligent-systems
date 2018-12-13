import { Component, OnInit } from '@angular/core';

import {Movie} from './movie/movie';
import {MovieService} from '../movie.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  movies: Movie[];
  selectedMovie: Movie;

  constructor(private movieService: MovieService) {
  }

  ngOnInit() {
    this.getMovies();
  }

  getMovies(): void {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies,
        error => console.log(error));
  }

  onSelect(movie: Movie): void {
    this.selectedMovie = movie;
  }
}
