module.exports = function(config) {
    config.set({
        frameworks: ['jasmine'],
        files: [
            'src/**/*.ts',
            'src/**/*.spec.ts'
        ],
        preprocessors: {
            'src/**/*.ts': ['webpack']
        },
        webpack: {
            // webpack configuration
        },
        reporters: ['progress'],
        port: 9876,
        colors: true,
        logLevel: config.LOG_INFO,
        autoWatch: true,
        browsers: ['Chrome'],
        singleRun: false,
        concurrency: Infinity
    });
};