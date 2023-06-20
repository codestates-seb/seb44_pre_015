import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/questions': {
        target : 'https://1517-59-11-30-105.ngrok-free.app',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/questions/, ''),
        secure: false,
      }
    }
  }
})
