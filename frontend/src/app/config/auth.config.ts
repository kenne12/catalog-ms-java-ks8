import { PassedInitialConfig } from 'angular-auth-oidc-client';
import { environment } from '../../environments/environment';


export const authConfig: PassedInitialConfig = {
  config: {
    authority: environment.keycloakRealm,
    redirectUrl: window.location.origin,
    postLogoutRedirectUri: window.location.origin,
    clientId: environment.clientId,
    scope: 'openid profile offline_access',
    responseType: 'code',
    silentRenew: true,
    useRefreshToken: true,
    renewTimeBeforeTokenExpiresInSeconds: 30,
  }
}
