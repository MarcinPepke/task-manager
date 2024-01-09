/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: true,
    images: {
        unoptimized: true,
    },
    async rewrites() {
        return [
            {
                source: '/task/:path*',
                destination: 'http://localhost:8090/task/:path*',
            },
        ]
    },
}
module.exports = nextConfig