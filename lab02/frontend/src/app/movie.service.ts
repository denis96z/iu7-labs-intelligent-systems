import {Observable} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Movie} from './movies/movie/movie';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getMovie(movieId: string): Observable<Movie> {
    return this.http.get<Movie>(this.apiUrl + '/movies/' + movieId);
  }

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.apiUrl + '/movies');
  }
}
