import { inject } from 'vue';

function injectStrict(key, fallback) {
  const resolved = inject(key, fallback);
  if (!resolved) {
    throw new Error(`Could not resolve ${key.description}`);
  }

  return resolved;
}

export { injectStrict };
