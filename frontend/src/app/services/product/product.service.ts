import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product } from "../../model/product";
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  getProducts(): Observable<Array<Product>> {
    return this.httpClient.get<Array<Product>>(`${environment.apiBaseUrl}/products`);
  }

  createProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(`${environment.apiBaseUrl}/products`, product);
  }
}
