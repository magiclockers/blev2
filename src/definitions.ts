declare module '@capacitor/core' {
  interface PluginRegistry {
    // eslint-disable-next-line @typescript-eslint/naming-convention
    ContactsPlugin: ContactsPluginPlugin;
  }
}

export interface ContactsPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getContacts(options: { value: string }): Promise<{ value: string }>;
}
