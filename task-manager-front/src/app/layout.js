export const metadata = {
  title: 'Next.js',
  description: 'Generated by Next.js',
}
import './global.css'

export default function RootLayout({ children }) {
 return (
    <html lang="en">
      <body className={"test"}>{children}</body>
    </html>
  )
}
