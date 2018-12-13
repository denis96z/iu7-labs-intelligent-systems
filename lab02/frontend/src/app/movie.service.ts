import {Observable, of} from 'rxjs';
import { Injectable } from '@angular/core';

import {Movie} from './movies/movie/movie';
import {MOVIES} from './movies/mock-movies';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  static getMovies(): Observable<Movie[]> {
    return of(MOVIES);
  }
}
