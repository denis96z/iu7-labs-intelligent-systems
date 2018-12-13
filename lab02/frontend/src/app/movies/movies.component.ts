import { Component, OnInit } from '@angular/core';

import {Movie} from './movie/movie';
import {MOVIES} from './mock-movies';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  movies = MOVIES;
  selectedMovie: Movie;

  ngOnInit() {
  }

  onSelect(movie: Movie): void {
    this.selectedMovie = movie;
  }
}
