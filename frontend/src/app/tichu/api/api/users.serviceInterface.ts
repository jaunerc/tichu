/**
 * Tichu backend api
 * Api for the Tichu backend
 *
 * The version of the OpenAPI document: 0.0.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import {HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

import {User} from '../model/models';


import {Configuration} from '../configuration';


export interface UsersServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;

    /**
     * Creates a user
     *
     * @param userName The name of the user
     */
    createUser(userName: string, extraHttpRequestParams?: any): Observable<User>;

}
