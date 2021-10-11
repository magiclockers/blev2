import { WebPlugin } from '@capacitor/core';
import { ContactsPluginPlugin } from './definitions';

export class ContactsPluginWeb extends WebPlugin implements ContactsPluginPlugin {
  constructor() {
    super({
      name: 'ContactsPlugin',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async getContacts(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

// eslint-disable-next-line @typescript-eslint/naming-convention
const ContactsPlugin = new ContactsPluginWeb();

export { ContactsPlugin };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(ContactsPlugin);


export * from './definitions';
